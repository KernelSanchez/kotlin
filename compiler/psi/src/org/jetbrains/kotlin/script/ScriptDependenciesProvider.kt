/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.script

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import kotlin.script.experimental.dependencies.ScriptDependencies

interface ScriptDependenciesProvider {
    fun getScriptDependencies(file: VirtualFile): ScriptDependencies?
    fun getScriptDependencies(file: PsiFile) =
        // TODO: implement proper support for in-memory-only psi files
        file.virtualFile?.let {
            getScriptDependencies(it)
        }

    companion object {
        fun getInstance(project: Project): ScriptDependenciesProvider =
            ServiceManager.getService(project, ScriptDependenciesProvider::class.java)
    }
}
