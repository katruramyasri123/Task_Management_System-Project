package com.rgukt.in.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgukt.in.enums.TaskStatus;
import com.rgukt.in.model.Task;
import com.rgukt.in.repo.TaskRepo;
import com.rgukt.in.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
	 @Autowired
	 private TaskRepo taskRepo;
	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {
		if(!requesterRole.equals("ROLE_ADMIN")) {
			throw new Exception("Only Admin can create the task");
		}
		task.setStatus(TaskStatus.PENDING);
		task.setCreatedAt(LocalDateTime.now());
		return taskRepo.save(task);
	}

	@Override
	public List<Task> getTaskByAssignedUserId(Long userId, TaskStatus status) {
		if(status==null) {
			return taskRepo.findByAssignedUserId(userId);
		}
		return taskRepo.findByAssignedUserIdAndStatus(userId, status);
	}

	@Override
	public List<Task> getAllTasks(TaskStatus status) {
		if(status==null) {
			return taskRepo.findAll();
		}
		return taskRepo.findByStatus(status);
	}

	@Override
	public Task updateTask(Long id, Task updateTask, Long userId) throws Exception {
		Task existingTask=getById(id);
		if(updateTask.getTitle()!=null) {
			existingTask.setTitle(updateTask.getTitle());
		}
		if(updateTask.getDescription()!=null) {
			existingTask.setDescription(updateTask.getDescription());

		}
		if(updateTask.getImage()!=null) {
			existingTask.setImage(updateTask.getImage());
		}
		if(updateTask.getStatus()!=null) {
			existingTask.setStatus(updateTask.getStatus());
		}
		if(updateTask.getDeadLine()!=null) {
			existingTask.setDeadLine(updateTask.getDeadLine());
		}
		existingTask.setAssignedUserId(userId);
		taskRepo.save(existingTask);
		return existingTask;
	}

	@Override
	public Task getById(Long id) throws Exception {
		return taskRepo.findById(id)
			    .orElseThrow(() -> new Exception("Task not found with id " + id));

	}

	@Override
	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
	}

	@Override
	public Task assignedToUser(Long userId, Long taskId) throws Exception {
		Task task=taskRepo.findById(taskId).get();
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.ASSIGNED);
		taskRepo.save(task);
		return task;
	}

	@Override
	public Task completeTask(Long taskId) throws Exception {
		Task task=getById(taskId);
		task.setStatus(TaskStatus.DONE);
		return taskRepo.save(task);
	}

}
