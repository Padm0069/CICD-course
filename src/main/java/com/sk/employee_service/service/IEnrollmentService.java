package com.sk.employee_service.service;

import com.sk.employee_service.dto.EnrollmentDto;

public interface IEnrollmentService {
    void createEnrollment(EnrollmentDto enrollmentDto);
    EnrollmentDto getCourseDetailsByEmployeeId(Integer employeeId);
    boolean deleteEnrollment(Integer employeeId, Integer courseId);

    Integer getEmployeeIdByMobileNumber(String mobileNumber);
}
