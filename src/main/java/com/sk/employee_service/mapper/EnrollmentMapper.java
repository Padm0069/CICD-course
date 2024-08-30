package com.sk.employee_service.mapper;

import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.entity.Enrollment;

public class EnrollmentMapper {

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto, Enrollment enrollment) {
        enrollment.setId(enrollmentDto.getId());
        enrollment.setEmployeeId(enrollmentDto.getEmployeeId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setStatus(enrollmentDto.getStatus());
        return enrollment;
    }

    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment, EnrollmentDto enrollmentDto) {
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setEmployeeId(enrollment.getEmployeeId());
        enrollmentDto.setCourseId(enrollment.getCourseId());
        enrollmentDto.setStatus(enrollment.getStatus());
        return enrollmentDto;
    }
}
