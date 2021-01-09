package com.github.martynagil.drugstoremanagement.controller;

import com.github.martynagil.drugstoremanagement.service.WorkTimeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
