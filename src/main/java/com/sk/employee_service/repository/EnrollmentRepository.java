package com.sk.employee_service.repository;

import com.sk.employee_service.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    Enrollment findByEmployeeId(Integer employeeId);
    Enrollment findByEmployeeIdAndCourseId(Integer employeeId, Integer courseId);
}
