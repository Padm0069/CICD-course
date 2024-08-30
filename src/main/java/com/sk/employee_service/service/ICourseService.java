package com.sk.employee_service.service;

import com.sk.employee_service.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    void createCourse(CourseDto courseDto, String mobileNumber);
    CourseDto fetchCourse(Integer id);
    List<CourseDto> fetchAllCourses();
    boolean updateCourse(CourseDto courseDto);
    boolean deleteCourse(Integer id);
}
