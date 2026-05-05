package com.jakeclara.taskflow.model;

/**
 * class to define task objects
 */
public class Task {

	// member attributes
	private final String taskID; // immutable
	private String name;
	private String description;

	// constructor
	public Task(String taskID, String name, String description) {
		this.taskID = validateTaskID(taskID);
		setName(name);
		setDescription(description);
	}

	// getter methods
	public String getTaskID() {
		return taskID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	// validation and setter methods

	// unique task ID String that cannot be longer than 10 characters
	// the task ID shall not be null and shall not be updatable
	// added empty field validation
	public String validateTaskID(String taskID) {
		if (taskID == null || taskID.length() > 10 || taskID.trim().isEmpty()) {
			throw new IllegalArgumentException("Task ID is required and cannot be more than 10 characters.");
		}
		// return instead of setting because attribute is immutable
		return taskID;
	}

	// name String field that cannot be longer than 20 characters
	// the name field shall not be null
	// added empty field validation
	public void setName(String name) {
		if (name == null || name.length() > 20 || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Task Name is required and cannot be more than 20 characters.");
		}
		this.name = name;
	}

	// description String field that cannot be longer than 50 characters
	// the description field shall not be null
	// added empty field validation
	public void setDescription(String description) {
		if (description == null || description.length() > 50 || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Task Description is required and cannot be more than 10 characters.");
		}
		this.description = description;
	}
}