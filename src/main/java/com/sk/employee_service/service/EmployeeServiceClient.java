package com.sk.employee_service.service;

import com.sk.employee_service.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-service", url = "http://localhost:8080")
public interface EmployeeServiceClient {

    @GetMapping("/api/fetch-employee-id")
    EmployeeDto getEmployeeByMobileNumber(@RequestParam("mobileNumber") String mobileNumber);
}
