package com.sk.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer id; // Add this field
    private Integer employeeId; // Use the employee ID directly in DTO
    private String title;
    private String duration;
    private String author;
    private String enrollmentStatus; // New field for enrollment details

    public CourseDto(int i, String javaProgramming, String s, String johnDoe, String enrolled) {
    }
}
