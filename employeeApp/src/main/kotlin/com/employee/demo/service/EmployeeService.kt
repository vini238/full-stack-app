package com.employee.demo.service

import com.employee.demo.model.Employee
import com.employee.demo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService {

    @Autowired
    lateinit var repository: EmployeeRepository

    fun getAllEmployees() = repository.findAll()

    fun saveEmployee(employee: Employee): Employee {
        val existingEmployee = repository.findByNameAndEmail(employee.name, employee.email)

        if (repository.existsById(employee.id)) {
            throw IllegalArgumentException("Employee with ID ${employee.id} already exists")
        }
        if (existingEmployee != null) {
            throw IllegalArgumentException("An employee with name ${employee.name} and email ${employee.email} already exists")
        }

        repository.save(employee)
        return employee
    }

    fun updateEmployee(id: UUID, updatedEmployee: Employee): Employee {
        val existingEmployee = repository.findById(id).orElseThrow {
            IllegalArgumentException("Employee with ID $id does not exist")
        }
        if (updatedEmployee.name.isNotEmpty()) {
            existingEmployee.name = updatedEmployee.name
        }
        if (updatedEmployee.email.isNotEmpty()) {
            existingEmployee.email = updatedEmployee.email
        }
        repository.save(existingEmployee)
        return existingEmployee
    }

    fun deleteEmployee(id: UUID) {
        if (!repository.existsById(id)) {
            throw IllegalArgumentException("Employee with ID ${id} does not exist")
        }

        repository.deleteById(id)
    }
}