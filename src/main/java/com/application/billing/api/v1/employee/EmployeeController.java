package com.application.billing.api.v1.employee;

import com.application.billing.api.v1.employee.pojo.CreateEmployeeBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("create")
    public Map<String, String> createEmployee(@RequestBody CreateEmployeeBody createEmployeeBody) {
        return employeeService.createEmployee(createEmployeeBody);
    }

}
