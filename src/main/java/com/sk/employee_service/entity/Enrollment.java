package com.sk.employee_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Employee ID cannot be null")
    @Column(name = "employee_id")
    private Integer employeeId;

    @NotNull(message = "Course ID cannot be null")
    @Column(name = "course_id")
    private Integer courseId;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private String status;
}
