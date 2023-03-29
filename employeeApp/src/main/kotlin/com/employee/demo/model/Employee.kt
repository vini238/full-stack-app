package com.employee.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.UUID

@AllArgsConstructor
@NoArgsConstructor
@Entity
data class Employee(
        @Id val id: UUID,
        var name: String,
        var email: String
)