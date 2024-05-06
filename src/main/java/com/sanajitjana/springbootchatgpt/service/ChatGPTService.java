package com.sanajitjana.springbootchatgpt.service;

import com.google.gson.Gson;
import com.sanajitjana.springbootchatgpt.controller.ChatGPTController;
import com.sanajitjana.springbootchatgpt.request.ChatGPTRequest;
import com.sanajitjana.springbootchatgpt.response.ChatGPTResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class ChatGPTService {

    private static final Logger log = LoggerFactory.getLogger(ChatGPTController.class);

    @Value("${OPEN_AI_URL}")
    private String OPEN_AI_URL;

    @Value("${OPEN_AI_KEY}")
    private String OPEN_AI_KEY;

    public String getResponse(String query) {

        log.info("Request enter into service: " + query);

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setPrompt(query);

        HttpPost post = new HttpPost(OPEN_AI_URL);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + OPEN_AI_KEY);

        Gson gson = new Gson();
        String body = gson.toJson(chatGPTRequest);

        log.info("Request body: " + body);

        try {
            final StringEntity entity;
            try {
                entity = new StringEntity(body);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            post.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                String responseBody = EntityUtils.toString(response.getEntity());

                log.info("responseBody: " + responseBody);

                ChatGPTResponse chatGPTResponse = gson.fromJson(responseBody, ChatGPTResponse.class);
                return chatGPTResponse.getChoices().get(0).getText();

            } catch (Exception e) {
                return "API Error";
            }

        } catch (Exception e) {
            return "Fetch Failed";

        }


    }

}
