package com.sanajitjana.springbootchatgpt.response;

import com.sanajitjana.springbootchatgpt.model.ChatGPTChoice;
import lombok.Data;

import java.util.List;

@Data
public class ChatGPTResponse {

    private List<ChatGPTChoice> choices;

}
