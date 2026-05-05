package com.jakeclara.taskflow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jakeclara.taskflow.model.Task;
import com.jakeclara.taskflow.service.TaskService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
        return taskService.getTaskByID(id);
    }

    @PostMapping
    public void addTask(@RequestParam String name, @RequestParam String description) {
        taskService.addTask(name, description);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/{id}/name")
    public void updateName(@PathVariable String id, @RequestParam String name) {
        taskService.updateName(id, name);
    }

    @PostMapping("/{id}/description")
    public void updateDescription(@PathVariable String id, @RequestParam String description) {
        taskService.updateDescription(id, description);
    }
}
