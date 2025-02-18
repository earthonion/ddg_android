/*
 * Copyright (c) 2017 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.trackerdetection

import android.net.Uri

interface Client {

    enum class ClientType {
        BLOCKING,
        ALLOWLIST,
    }

    enum class ClientName(val type: ClientType) {
        // current clients
        TDS(ClientType.BLOCKING),

        // legacy clients
        EASYLIST(ClientType.BLOCKING),
        EASYPRIVACY(ClientType.BLOCKING),
        TRACKERSALLOWLIST(ClientType.ALLOWLIST),
    }

    data class Result(
        val matches: Boolean,
        val entityName: String? = null,
        val categories: List<String>? = null,
        val surrogate: String? = null,
        val isATracker: Boolean,
    )

    val name: ClientName

    fun matches(
        url: String,
        documentUrl: String,
        requestHeaders: Map<String, String>,
    ): Result

    fun matches(
        url: String,
        documentUrl: Uri,
        requestHeaders: Map<String, String>,
    ): Result

    fun matches(
        url: Uri,
        documentUrl: String,
        requestHeaders: Map<String, String>,
    ): Result

    fun matches(
        url: Uri,
        documentUrl: Uri,
        requestHeaders: Map<String, String>,
    ): Result
}
