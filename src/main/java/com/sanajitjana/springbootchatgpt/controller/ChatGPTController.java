package com.sanajitjana.springbootchatgpt.controller;

import com.sanajitjana.springbootchatgpt.request.SearchRequest;
import com.sanajitjana.springbootchatgpt.service.ChatGPTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChatGPTController {

    private static final Logger log = LoggerFactory.getLogger(ChatGPTController.class);

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/search")
    public String search(@RequestBody SearchRequest searchRequest){
        return chatGPTService.getResponse(searchRequest.getQuery());
    }
}
