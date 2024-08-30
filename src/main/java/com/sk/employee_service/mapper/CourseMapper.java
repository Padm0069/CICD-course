package com.sk.employee_service.mapper;

import com.sk.employee_service.dto.CourseDto;
import com.sk.employee_service.entity.Course;

public class CourseMapper {

    public static Course mapToCourse(CourseDto courseDto, Course course){
        if (course == null) {
            course = new Course();
        }
        course.setId(courseDto.getId());
        course.setTitle(courseDto.getTitle());
        course.setDuration(courseDto.getDuration());
        course.setAuthor(courseDto.getAuthor());
        return course;
    }

    public static CourseDto mapToCourseDto(Course course, CourseDto courseDto){
        if (courseDto == null) {
            courseDto = new CourseDto();
        }
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDuration(course.getDuration());
        courseDto.setAuthor(course.getAuthor());
        return courseDto;
    }
}
