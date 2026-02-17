package com.rgukt.in.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rgukt.in.enums.TaskStatus;
import com.rgukt.in.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
	public Optional<Task> findById(Long id);
	public List<Task> findByAssignedUserId(Long userId);
	public List<Task> findByStatus(TaskStatus status);
	public List<Task> findByAssignedUserIdAndStatus(Long userId, TaskStatus status);
	

}
