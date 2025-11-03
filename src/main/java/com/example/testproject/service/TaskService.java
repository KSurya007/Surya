package com.example.testproject.service;

import java.util.List;

import com.example.testproject.payload.TaskDto;

public interface TaskService {
	public TaskDto saveTask(long userid, TaskDto taskDto);

	public List<TaskDto> getAllTasks(long userid);

	public TaskDto getTask(long userid, long taskid);
}
