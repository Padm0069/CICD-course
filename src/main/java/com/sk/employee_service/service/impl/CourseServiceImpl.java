package com.sk.employee_service.service.impl;

import com.sk.employee_service.entity.Employee;
import com.sk.employee_service.service.EmployeeServiceClient;
import com.sk.employee_service.dto.CourseDto;
import com.sk.employee_service.entity.Course;
import com.sk.employee_service.mapper.CourseMapper;
import com.sk.employee_service.repository.CourseRepository;
import com.sk.employee_service.repository.EmployeeRepository; // Add this import
import com.sk.employee_service.service.ICourseService;
import com.sk.employee_service.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository; // Add this repository

    @Autowired
    private EmployeeServiceClient employeeServiceClient;

    @Override
    public void createCourse(CourseDto courseDto, String mobileNumber) {
        EmployeeDto employeeDto = employeeServiceClient.getEmployeeByMobileNumber(mobileNumber);
        Integer employeeId = employeeDto.getId();

        // Fetch the employee from the database
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new RuntimeException("Employee not found for ID - " + employeeId)
        );

        Course course = CourseMapper.mapToCourse(courseDto, new Course());
        course.setEmployee(employee); // Set the fetched employee reference
        courseRepository.save(course);
    }

    @Override
    public CourseDto fetchCourse(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found for ID - " + id)
        );

        return CourseMapper.mapToCourseDto(course, new CourseDto());
    }

    @Override
    public List<CourseDto> fetchAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> CourseMapper.mapToCourseDto(course, new CourseDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCourse(CourseDto courseDto) {
        if (courseDto.getId() == null) {
            return false;
        }
        Course course = courseRepository.findById(courseDto.getId()).orElseThrow(
                () -> new RuntimeException("Course not found for ID - " + courseDto.getId())
        );

        Course updatedCourse = CourseMapper.mapToCourse(courseDto, course);
        courseRepository.save(updatedCourse);
        return true;
    }

    @Override
    public boolean deleteCourse(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found for ID - " + id)
        );
        courseRepository.delete(course);
        return true;
    }
}
