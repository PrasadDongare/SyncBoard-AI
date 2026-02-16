package com.prasad.syncboard.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    @Autowired(required = false)
    private ChatModel chatModel;

    public String enhanceTaskDescription(String title) {
        try {
            // If we have a key and it works, use AI
            if (chatModel != null) {
                return chatModel.call("Write a 1-sentence description for: " + title);
            }
        } catch (Exception e) {
            // If the key is fake or network fails, log it and return fallback text
            System.out.println("AI Note: Using fallback description. (Key not configured)");
        }

        return "Professional breakdown for " + title + " (SyncBoard AI System)";
    }
}