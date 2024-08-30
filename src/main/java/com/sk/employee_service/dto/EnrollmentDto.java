package com.sk.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    private Integer id;  // Added ID for consistency in updates
    private Integer employeeId;
    private Integer courseId;
    private String status;
}
