package com.employee.demo.service

import com.employee.demo.model.Employee
import com.employee.demo.repository.EmployeeRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID


@ExtendWith(MockitoExtension::class)
class EmployeeServiceTest {

    @InjectMocks
    lateinit var employeeService: EmployeeService

    @Mock
    lateinit var employeeRepository: EmployeeRepository

    @Test
    fun `given get call, when calling getAllEmployees(), then return employees`() {
        val employees = listOf(Employee(UUID.randomUUID(), "John", "Doe"))
        `when`(employeeRepository.findAll()).thenReturn(employees)

        val result = employeeService.getAllEmployees()

        assertEquals(employees, result)
    }

    @Test
    fun `given valid employee to save, when calling saveEmployee(), then return create employee`() {
        val employee = Employee(UUID.randomUUID(), "John", "Doe")
        `when`(employeeRepository.existsById(employee.id)).thenReturn(false)
        `when`(employeeRepository.save(any(Employee::class.java))).thenReturn(employee)

        val result = employeeService.saveEmployee(employee)

        assertEquals(employee, result)
    }

    @Test
    fun `given valid id and customer, when calling updateEmployee(), then update customer`() {
        val employeeId = UUID.randomUUID()
        val employee = Employee(employeeId, "John", "Doe")
        `when`(employeeRepository.existsById(employeeId)).thenReturn(true)
        `when`(employeeRepository.save(any(Employee::class.java))).thenReturn(employee)

        val result = employeeService.updateEmployee(employeeId, employee)

        assertEquals(employee, result)
    }

    @Test
    fun `given valid id, when calling deleteEmployee(), then delete employee`() {
        val employeeId = UUID.randomUUID()
        `when`(employeeRepository.existsById(employeeId)).thenReturn(true)

        employeeService.deleteEmployee(employeeId)

        `when`(employeeRepository.existsById(employeeId)).thenReturn(false)
        assertThrows(IllegalArgumentException::class.java) {
            employeeService.deleteEmployee(employeeId)
        }
    }
}