package com.prasad.syncboard.controller;

@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/tasks")
public class TaskController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.prasad.syncboard.service.TaskService taskService;

    @org.springframework.web.bind.annotation.GetMapping
    public java.util.List<com.prasad.syncboard.entity.Task> getTasks() {
        return taskService.getAllTasks();
    }

    @org.springframework.web.bind.annotation.PostMapping
    public com.prasad.syncboard.entity.Task createTask(@org.springframework.web.bind.annotation.RequestBody com.prasad.syncboard.entity.Task task) {
        return taskService.saveTask(task);
    }
}