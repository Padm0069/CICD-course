package com.sk.employee_service.service.impl;

import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.entity.Enrollment;
import com.sk.employee_service.repository.EnrollmentRepository;
import com.sk.employee_service.service.IEnrollmentService;
import com.sk.employee_service.service.IEmployeeService; // Import the EmployeeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private IEmployeeService employeeService; // Use EmployeeService

    @Override
    public void createEnrollment(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEmployeeId(enrollmentDto.getEmployeeId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setStatus(enrollmentDto.getStatus());
        enrollmentRepository.save(enrollment);
    }

    @Override
    public EnrollmentDto getCourseDetailsByEmployeeId(Integer employeeId) {
        Enrollment enrollment = enrollmentRepository.findByEmployeeId(employeeId);
        // Convert to DTO
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEmployeeId(enrollment.getEmployeeId());
        enrollmentDto.setCourseId(enrollment.getCourseId());
        enrollmentDto.setStatus(enrollment.getStatus());
        return enrollmentDto;
    }

    @Override
    public boolean deleteEnrollment(Integer employeeId, Integer courseId) {
        Enrollment enrollment = enrollmentRepository.findByEmployeeIdAndCourseId(employeeId, courseId);
        if (enrollment != null) {
            enrollmentRepository.delete(enrollment);
            return true;
        }
        return false;
    }

    @Override
    public Integer getEmployeeIdByMobileNumber(String mobileNumber) {
        return employeeService.getEmployeeIdByMobileNumber(mobileNumber); // Call EmployeeService
    }
}
