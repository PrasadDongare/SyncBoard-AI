package com.prasad.syncboard.controller;

import com.prasad.syncboard.entity.Task;
import com.prasad.syncboard.service.AiService;
import com.prasad.syncboard.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // This is the "Megaphone" that sends messages to WebSockets
    @Autowired
    private org.springframework.messaging.simp.SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);

        // This broadcasts the saved task to anyone listening to "/topic/tasks"
        messagingTemplate.convertAndSend("/topic/tasks", savedTask);

        return savedTask;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }
    @Autowired
    private AiService aiService;

    @PostMapping("/ai-generate")
    public Task createAiTask(@RequestBody Task task) {
        System.out.println("AI Task Request received for: " + task.getTitle());
        // 1. Use AI to generate a professional description
        String enhancedDescription = aiService.enhanceTaskDescription(task.getTitle());
        task.setDescription(enhancedDescription);

        // 2. Save to Database
        Task savedTask = taskService.saveTask(task);

        // 3. Broadcast to WebSockets
        messagingTemplate.convertAndSend("/topic/tasks", savedTask);

        return savedTask;
    }
}