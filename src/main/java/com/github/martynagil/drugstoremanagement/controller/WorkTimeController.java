package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.model.WorkTime;
import com.github.martynagil.drugstoremanagement.service.WorkTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkTimeController {

    private WorkTimeService workTimeService;

    public WorkTimeController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @PostMapping("/employees/{employeeId}/workTime/start")
    public void startWork(@PathVariable Long employeeId) {
        workTimeService.startWork(employeeId);
    }

    @PostMapping("/employees/{employeeId}/workTime/end")
    public void endWork(@PathVariable Long employeeId) {
        workTimeService.endWork(employeeId);
    }

    @GetMapping("/employees/{employeeId}/workTime/month")
    public List<WorkTime> getMonthlyWorkTimes(@PathVariable Long employeeId) {
        return workTimeService.getMonthlyWorkTimes(employeeId);
    }
}
