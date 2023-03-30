package com.employee.demo.repository

import com.employee.demo.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EmployeeRepository : JpaRepository<Employee, UUID> {
    fun findByNameAndEmail(name: String, email: String): Employee?
}