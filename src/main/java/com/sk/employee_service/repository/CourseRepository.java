package com.sk.employee_service.repository;

import com.sk.employee_service.entity.Course;
import com.sk.employee_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByEmployeeId(Integer employeeId);

    void deleteByEmployeeId(Integer employeeId);
}
