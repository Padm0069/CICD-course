package com.sk.employee_service.controller;

import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createEnrollment(@RequestParam String mobileNumber, @RequestParam Integer courseId) {
        // Fetch employee ID using mobile number
        Integer employeeId = enrollmentService.getEmployeeIdByMobileNumber(mobileNumber);

        // Create enrollment
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEmployeeId(employeeId);
        enrollmentDto.setCourseId(courseId);
        enrollmentDto.setStatus("ENROLLED"); // Or any appropriate status

        enrollmentService.createEnrollment(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Enrollment created successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEnrollment(@RequestParam String mobileNumber, @RequestParam Integer courseId) {
        // Fetch employee ID using mobile number
        Integer employeeId = enrollmentService.getEmployeeIdByMobileNumber(mobileNumber);

        // Delete enrollment
        boolean isDeleted = enrollmentService.deleteEnrollment(employeeId, courseId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Enrollment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enrollment not found");
        }
    }

    @GetMapping("/course-details")
    public ResponseEntity<EnrollmentDto> getCourseDetails(@RequestParam Integer employeeId) {
        EnrollmentDto enrollmentDto = enrollmentService.getCourseDetailsByEmployeeId(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentDto);
    }
}
