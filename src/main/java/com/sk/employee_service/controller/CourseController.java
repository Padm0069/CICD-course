package com.sk.employee_service.controller;

import com.sk.employee_service.dto.CourseDto;
import com.sk.employee_service.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course Controller", description = "Course Controller for CRUD operations")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Operation(description = "Create new Course", summary = "Post API to create new course")
    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestBody CourseDto courseDto,
                                               @RequestParam String mobileNumber) {
        courseService.createCourse(courseDto, mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully");
    }

    @Operation(description = "Fetch Course by ID", summary = "Get API to fetch course details by ID")
    @GetMapping("/fetch")
    public ResponseEntity<CourseDto> fetchCourse(@RequestParam Integer id) {
        CourseDto courseDto = courseService.fetchCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseDto);
    }

    @Operation(description = "Update existing Course", summary = "Put API to update course details")
    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestBody CourseDto courseDto) {
        boolean isUpdated = courseService.updateCourse(courseDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Course updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course not updated");
        }
    }

    @Operation(description = "Delete Course by ID", summary = "Delete API to remove course by ID")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam Integer id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course not deleted");
        }
    }

    @GetMapping("/greet")
    public String hello(){
        return "Hello World";
    }
}
