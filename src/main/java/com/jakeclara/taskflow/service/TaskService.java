package com.jakeclara.taskflow.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.jakeclara.taskflow.exception.TaskNotFoundException;
import com.jakeclara.taskflow.model.Task;
import com.jakeclara.taskflow.util.IdGenerator;

/**
 * class to manage task objects
 */
@Service
public class TaskService {

    // list to hold tasks
    private List<Task> taskList = new ArrayList<>();

    // unique ID generator
    private final IdGenerator idGenerator;

    // constructor
    public TaskService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    // begin CRUD methods
    /**
     * add tasks with a unique ID
     * 
     * @param name        the task name
     * @param description the task description
     * 
     * @return the new task object
     */
    public Task addTask(String name, String description) {
        // generate unique ID
        String taskID = idGenerator.generateUniqueID();
        // pass arguments to constructor to create a new task object
        Task newTask = new Task(taskID, name, description);
        // add new task object to the list
        taskList.add(newTask);
        // return the new task object
        return newTask;
    }

    /**
     * deletes task per taskID
     * 
     * @param taskID the task's unique identifier
     */
    public void deleteTask(String taskID) {
        getTaskByID(taskID);
        taskList.removeIf(task -> task.getTaskID().equals(taskID));
    }

    /**
     * update name field per taskID
     * 
     * @param taskID  the task's unique identifier
     * @param newName the task's updated name
     * 
     * @return the updated task object
     */
    public Task updateName(String taskID, String newName) {
        Task task = getTaskByID(taskID);
        task.setName(newName);
        return task;
    }

    /**
     * update description field per taskID
     * 
     * @param taskID         the task's unique identifier
     * @param newDescription the task's updated description
     * 
     * @return the updated task object
     */
    public Task updateDescription(String taskID, String newDescription) {
        Task task = getTaskByID(taskID);
        task.setDescription(newDescription);
        return task;
    }

    /**
     * gets a task by id
     * or throws an exception if task does not exist
     * 
     * @param taskID the task's unique identifier
     * @return a task object
     */
    public Task getTaskByID(String taskID) {
        for (Task task : taskList) {
            if (task.getTaskID().equals(taskID)) {
                return task;
            }
        }
        throw new TaskNotFoundException("Task " + taskID + " not found.");
    }

    /**
     * gets all tasks
     * 
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        // return a new list to prevent external modification of the original list
        return new ArrayList<>(taskList);
    }
}