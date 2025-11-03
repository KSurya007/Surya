package com.example.testproject.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testproject.entity.Task;
import com.example.testproject.entity.Users;
import com.example.testproject.exception.APIException;
import com.example.testproject.exception.TaskNotFound;
import com.example.testproject.exception.UserNotFound;
import com.example.testproject.payload.TaskDto;
import com.example.testproject.repository.TaskRepository;
import com.example.testproject.repository.UserRepository;
import com.example.testproject.service.TaskService;

@Service
public class TaskServiceImple implements TaskService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto saveTask(long userid, TaskDto taskDto) {
		// TODO Auto-generated method stub
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("user Id %d is not found", userid)));
		Task task = modelMapper.map(taskDto, Task.class);
		task.setUsers(user);
		Task savedTask = taskRepository.save(task);
		return modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTasks(long userid) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("user Id %d is not found", userid)));
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
	}

	@Override
	public TaskDto getTask(long userid, long taskid) {
		// TODO Auto-generated method stub
		Users users = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("user Id %d is not found", userid)));
		Task task = taskRepository.findById(taskid)
				.orElseThrow(() -> new TaskNotFound(String.format("task id %d id not found", taskid)));
		if (users.getId() != task.getUsers().getId()) {
			throw new APIException(String.format("task id %d not belongs to User id %d", taskid, userid));
		}
		return modelMapper.map(task, TaskDto.class);
	}

}
