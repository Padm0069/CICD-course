package com.sk.employee_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.employee_service.dto.CourseDto;
import com.sk.employee_service.dto.ResponseDto;
import com.sk.employee_service.service.ICourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICourseService courseService;

    @Test
    @DisplayName("GET /api/courses/fetch?employeeId=1 - Found")
    public void fetchCourse() throws Exception {
        CourseDto courseDto = new CourseDto(1, "Java Programming", "6 months", "John Doe", "Enrolled");

        Mockito.when(courseService.fetchCourse(1)).thenReturn(courseDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/courses/fetch")
                        .param("employeeId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Java Programming")))
                .andExpect(jsonPath("$.duration", is("6 months")))
                .andExpect(jsonPath("$.author", is("John Doe")))
                .andExpect(jsonPath("$.enrollmentStatus", is("Enrolled")));
    }

    @Test
    @DisplayName("POST /api/courses/create - Create")
    public void createCourse() throws Exception {
        CourseDto courseDto = new CourseDto(2, "Spring Boot", "3 months", "Jane Smith", "Not Enrolled");

        doNothing().when(courseService).createCourse(courseDto, "9876543210"); // Mock mobile number

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/courses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Created successfully")))
                .andExpect(jsonPath("$.status", is("201")));
    }

    @Test
    @DisplayName("PUT /api/courses/update - Update")
    public void updateCourse() throws Exception {
        CourseDto courseDto = new CourseDto(1, "Java Programming", "6 months", "John Doe", "Enrolled");

        doReturn(true).when(courseService).updateCourse(courseDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/courses/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Updated Successfully")))
                .andExpect(jsonPath("$.status", is("203")));
    }

    @Test
    @DisplayName("PUT /api/courses/update - Update Failure")
    public void updateCourseFailure() throws Exception {
        CourseDto courseDto = new CourseDto(1, "Advanced Java", "6 months", "John Doe", "Not Enrolled");

        doReturn(false).when(courseService).updateCourse(courseDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/courses/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDto)))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Not updated")))
                .andExpect(jsonPath("$.status", is("501")));
    }

    @Test
    @DisplayName("DELETE /api/courses/delete?employeeId=1 - Delete")
    public void deleteCourse() throws Exception {
        Mockito.when(courseService.deleteCourse(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/courses/delete")
                        .param("employeeId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Deleted Successfully")))
                .andExpect(jsonPath("$.status", is("200")));
    }

    @Test
    @DisplayName("DELETE /api/courses/delete?employeeId=1 - Delete Failure")
    public void deleteCourseFailure() throws Exception {
        Mockito.when(courseService.deleteCourse(1)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/courses/delete")
                        .param("employeeId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", is("Not deleted")))
                .andExpect(jsonPath("$.status", is("501")));
    }
}
