package com.employee.demo.controller

import com.employee.demo.model.Employee
import com.employee.demo.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class EmployeeController {

    @Autowired
    lateinit var service: EmployeeService

    @GetMapping("/employees")
    fun retrieveAllEmployees() = service.getAllEmployees()

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createEmployee(@RequestBody employee: Employee): Employee {
        return service.saveEmployee(employee)
    }

    @PutMapping("/update/{id}")
    fun updateEmployee(@PathVariable id: UUID, @RequestBody employee: Employee): ResponseEntity<Employee> {
        val updatedEmployee = service.updateEmployee(id, employee)
        return ResponseEntity.ok(updatedEmployee)
    }


    @DeleteMapping("/delete/{id}")
    fun removeEmployee(@PathVariable id: UUID): ResponseEntity<Unit> {
        service.deleteEmployee(id)
        return ResponseEntity.noContent().build()
    }
}