/*
 * Copyright 2003-2025 The IdeaVim authors
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE.txt file or at
 * https://opensource.org/licenses/MIT.
 */

package com.maddyhome.idea.vim.thinapi

import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.maddyhome.idea.vim.diagnostic.vimLogger
import com.maddyhome.idea.vim.extension.ExtensionBean
import com.maddyhome.idea.vim.extension.KspExtensionBean
import com.maddyhome.idea.vim.extension.toExtensionBean
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

internal const val IDEAVIM_ID = "IdeaVIM"

fun IdeaPluginDescriptor.isIdeaVimExtension(): Boolean {
  return dependencies.any { it.pluginId.idString == IDEAVIM_ID }
}

@Service
class IjPluginExtensionsScanner {
  private val logger = vimLogger<IjPluginExtensionsScanner>()

  @OptIn(ExperimentalSerializationApi::class)
  fun scanPluginJar(pluginDescriptor: IdeaPluginDescriptor): List<ExtensionBean>? {
    val classLoader = pluginDescriptor.pluginClassLoader
    val pluginId = pluginDescriptor.pluginId.idString
    try {
      val inputStream: InputStream? = classLoader?.getResourceAsStream("META-INF/extensions.json")
      if (inputStream != null) {
        inputStream.use {
          val extensionBeans: List<KspExtensionBean> = Json.decodeFromStream(it)
          logger.debug(
            "Plugin ${pluginId}: Extensions: ${extensionBeans.joinToString(", ") { it.extensionName }}."
          )
          return extensionBeans.map { it.toExtensionBean(pluginId) }
        }
      } else {
        logger.debug("Plugin ${pluginId}: META-INF/extensions.json not found in the plugin JAR.")
      }
    } catch (e: Exception) {
      logger.error("Failed to scan plugin JAR for extensions: $pluginId", e)
    }
    return null
  }

  fun scanAllPlugins(): List<ExtensionBean> {
    val mutableList = mutableListOf<ExtensionBean>()
    PluginManagerCore.plugins.filter { plugin ->
      plugin.isEnabled && plugin.isIdeaVimExtension()
    }.forEach { plugin ->
      val extensions = scanPluginJar(plugin) ?: return@forEach
      mutableList.addAll(extensions)
    }
    return mutableList
  }

  companion object {
    fun instance() = service<IjPluginExtensionsScanner>()
  }
}