package com.sk.employee_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.service.IEnrollmentService;
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
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IEnrollmentService enrollmentService;

    @Test
    @DisplayName("POST /api/enrollments/create - Create Enrollment")
    public void createEnrollment() throws Exception {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEmployeeId(1);
        enrollmentDto.setCourseId(101);
        enrollmentDto.setStatus("ENROLLED");

        doNothing().when(enrollmentService).createEnrollment(enrollmentDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/enrollments/create")
                        .param("mobileNumber", "9876543210")
                        .param("courseId", "101")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Enrollment created successfully")));
    }

    @Test
    @DisplayName("DELETE /api/enrollments/delete - Delete Enrollment")
    public void deleteEnrollment() throws Exception {
        Integer employeeId = 1;
        Integer courseId = 101;
        doReturn(true).when(enrollmentService).deleteEnrollment(employeeId, courseId);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/enrollments/delete")
                        .param("mobileNumber", "9876543210")
                        .param("courseId", "101")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Enrollment deleted successfully")));
    }

    @Test
    @DisplayName("GET /api/enrollments/course-details - Fetch Course Details")
    public void getCourseDetails() throws Exception {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setEmployeeId(1);
        enrollmentDto.setCourseId(101);
        enrollmentDto.setStatus("ENROLLED");

        doReturn(enrollmentDto).when(enrollmentService).getCourseDetailsByEmployeeId(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/enrollments/course-details")
                        .param("employeeId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId", is(1)))
                .andExpect(jsonPath("$.courseId", is(101)))
                .andExpect(jsonPath("$.status", is("ENROLLED")));
    }
}
