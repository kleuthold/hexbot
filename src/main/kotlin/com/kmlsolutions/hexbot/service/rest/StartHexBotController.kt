package com.kmlsolutions.hexbot.service.rest

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StartHexBotController {
    @GetMapping("/start")
    fun start(): String {
        log.info("start")
        return "Started"
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}