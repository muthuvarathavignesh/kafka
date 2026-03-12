package com.mvv.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mvv.demo.dto.UserEvent;
import com.mvv.demo.service.kafka.KafkaProducerService;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producerService;

    @PostMapping
    public String publish(@RequestBody UserEvent event) {
        producerService.sendEvent(event);
        return "Event Published Successfully";
    }
}

