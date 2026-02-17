package com.rgukt.in.service;

import java.util.List;

import com.rgukt.in.enums.TaskStatus;
import com.rgukt.in.model.Task;

public interface TaskService {
	
	public Task createTask(Task task,String requesterRole) throws Exception;
	public List<Task> getTaskByAssignedUserId(Long userId,TaskStatus status);
	public List<Task> getAllTasks(TaskStatus status);
	public Task updateTask(Long id, Task updateTask, Long userId ) throws Exception;
	public Task getById(Long id) throws Exception;
	public void deleteTask(Long id);
	public Task assignedToUser(Long userId, Long taskId) throws Exception;
	public Task completeTask(Long taskId) throws Exception;
	
	
	
	
	
}
