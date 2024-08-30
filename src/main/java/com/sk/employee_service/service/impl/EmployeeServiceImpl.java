package com.sk.employee_service.service.impl;

import com.sk.employee_service.dto.EmployeeDto;
import com.sk.employee_service.entity.Employee;
import com.sk.employee_service.repository.EmployeeRepository;
import com.sk.employee_service.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Integer getEmployeeIdByMobileNumber(String mobileNumber) {
        Employee employee = employeeRepository.findByMobileNumber(mobileNumber);
        if (employee != null) {
            return employee.getId();
        }
        throw new RuntimeException("Employee not found for mobile number - " + mobileNumber);
    }
}
