package com.rve.rvkernelmanager.data.repository

import com.rve.rvkernelmanager.data.model.GitHubRelease
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class GitHubRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }

    suspend fun getLatestVersion(repoPath: String): String? {
        return try {
            val response: GitHubRelease = client
                .get("https://api.github.com/repos/$repoPath/releases/latest")
                .body()
            response.tagName
        } catch (e: Exception) {
            println("Error fetching version for $repoPath: ${e.message}")
            null
        }
    }
}