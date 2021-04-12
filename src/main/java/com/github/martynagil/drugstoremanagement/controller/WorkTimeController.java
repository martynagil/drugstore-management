package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.model.WorkTime;
import com.github.martynagil.drugstoremanagement.service.WorkTimeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees/{employeeId}/work-time")
public class WorkTimeController {

	private WorkTimeService workTimeService;

	public WorkTimeController(WorkTimeService workTimeService) {
		this.workTimeService = workTimeService;
	}

	@PostMapping("/start")
	public void startWork(@PathVariable Long employeeId) {
		workTimeService.startWork(employeeId);
	}

	@PostMapping("/end")
	public void endWork(@PathVariable Long employeeId) {
		workTimeService.endWork(employeeId);
	}

	@GetMapping("/month")
	public List<WorkTime> getMonthlyWorkTimes(@PathVariable Long employeeId) {
		return workTimeService.getMonthlyWorkTimes(employeeId);
	}
}
