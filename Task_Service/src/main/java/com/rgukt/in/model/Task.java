package com.rgukt.in.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rgukt.in.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	private String image;
	private Long assignedUserId;
	private List<String> tags=new ArrayList<>();
	private LocalDateTime deadLine;
	private LocalDateTime createdAt;
	private TaskStatus status;
	
	
	
}
