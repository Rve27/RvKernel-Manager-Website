package com.rve.rvkernelmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform