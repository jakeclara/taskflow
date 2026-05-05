package com.jakeclara.taskflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.jakeclara.taskflow.exception.TaskNotFoundException;
import com.jakeclara.taskflow.model.Task;

/**
 * class to manage task objects
 */
@Service
public class TaskService {

    // list to hold tasks
    private List<Task> taskList = new ArrayList<>();

    // member variable for unique id generation
    private final AtomicLong nextUniqueId = new AtomicLong(1);

    // constructor
    public TaskService() {
    }

    // begin CRUD methods
    /**
     * add tasks with a unique ID
     * 
     * @param name        the task name
     * @param description the task description
     */
    public void addTask(String name, String description) {
        // generate unique ID
        String taskID = generateUniqueID();
        // pass arguments to constructor to create a new task object
        Task newTask = new Task(taskID, name, description);
        // add new task object to the list
        taskList.add(newTask);
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
     */
    public void updateName(String taskID, String newName) {
        Task task = getTaskByID(taskID);
        task.setName(newName);
    }

    /**
     * update description field per taskID
     * 
     * @param taskID         the task's unique identifier
     * @param newDescription the task's updated description
     */
    public void updateDescription(String taskID, String newDescription) {
        Task task = getTaskByID(taskID);
        task.setDescription(newDescription);
    }

    // utility methods
    /**
     * generates a unique ID using the AtomicLong class member attribute
     * adapted from:
     * https://stackoverflow.com/questions/8938528/how-do-i-get-a-unique-id-per-object-in-java
     * 
     * @return a unique ID as String
     */
    private String generateUniqueID() {
        // create a unique identifier by getting and incrementing nextUniqueId
        long uniqueID = nextUniqueId.getAndIncrement();

        // return the unique identifier as a String
        return Long.toString(uniqueID);
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
}