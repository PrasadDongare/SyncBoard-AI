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

    @PatchMapping("/{id}/next")
    public Task moveTaskToNextStatus(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);

        String currentStatus = task.getStatus();
        if ("TODO".equals(currentStatus)) {
            task.setStatus("IN_PROGRESS");
        } else if ("IN_PROGRESS".equals(currentStatus)) {
            task.setStatus("DONE");
        }
        Task updatedTask = taskService.saveTask(task);

        // Broadcast the update so the UI moves the card in real-time
        messagingTemplate.convertAndSend("/topic/tasks", updatedTask);

        return updatedTask;
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id); // Implement this in Service (repository.deleteById(id))

        // Broadcast a special 'deleted' message to the WebSocket
        // We send the ID so the frontend knows which card to vanish
        Task deletedSignal = new Task();
        deletedSignal.setId(id);
        deletedSignal.setDeleted(true);

        messagingTemplate.convertAndSend("/topic/tasks", deletedSignal);
    }
}