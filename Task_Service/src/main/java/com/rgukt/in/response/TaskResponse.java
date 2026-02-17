package com.rgukt.in.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rgukt.in.enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
	private String title;
	private String description;
	private String image;
	private TaskStatus status;
	private List<String> tags=new ArrayList<>();
	private LocalDateTime deadLine;
	private LocalDateTime createdAt;
	private Long id;


}
