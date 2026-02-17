package com.rgukt.in.clientsDto;

public enum TaskStatus {
	PENDING("PENDING"),
	ASSIGNED("ASSIGNED"),
	DONE("DONE");
	private final String status;
	private TaskStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}

}
