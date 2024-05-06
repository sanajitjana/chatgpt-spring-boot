package com.sanajitjana.springbootchatgpt.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ChatGPTRequest {

    private String model="davinci-002";
    private String prompt;
    private int temperature=1;

    @SerializedName(value = "max_tokens")
    private int maxTokens=256;

}
