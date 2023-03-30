package com.employee.demo.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.UUID

@AllArgsConstructor
@NoArgsConstructor
@Entity
data class Employee(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID = UUID.randomUUID(),
        var name: String,
        var email: String
)