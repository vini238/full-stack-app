package com.employee.demo.controller

import com.employee.demo.model.Employee
import com.employee.demo.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.UUID

@WebMvcTest(EmployeeController::class)
class EmployeeControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var service: EmployeeService

    @Test
    fun `given valid get request, when calling retrieveAllEmployees(), then return customers`() {
        mvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    fun `given create valid create request, when calling createEmployee(), then return success`() {
        val employee = Employee(UUID.randomUUID(), "test", "test@test.com")
        val requestBody = ObjectMapper().writeValueAsString(employee)

        Mockito.`when`(service.saveEmployee(employee)).thenReturn(employee)

        val result = mvc.perform(
                post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )

        result.andExpect(status().is2xxSuccessful)
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        result.andExpect(jsonPath("$.id").value(employee.id.toString()))
        result.andExpect(jsonPath("$.name").value(employee.name))
        result.andExpect(jsonPath("$.email").value(employee.email))
    }

    @Test
    fun `given valid update request, when calling updateEmployee(), then return updated employee`() {
        val id = UUID.randomUUID()
        val employee = Employee(id, "test", "test@test.com")
        val requestBody = ObjectMapper().writeValueAsString(employee)

        Mockito.`when`(service.updateEmployee(id, employee)).thenReturn(employee)

        val result = mvc.perform(
                put("/update/$id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )

        result.andExpect(status().isOk)
        result.andExpect(content().contentType(MediaType.APPLICATION_JSON))
        result.andExpect(jsonPath("$.id").value(employee.id.toString()))
        result.andExpect(jsonPath("$.name").value(employee.name))
        result.andExpect(jsonPath("$.email").value(employee.email))
    }

    @Test
    fun `given valid delete request, when calling deleteEmployee(), then return success`() {
        val employeeId = UUID.randomUUID()

        val result = mvc.perform(
                delete("/delete/$employeeId")
                        .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().is2xxSuccessful)
    }

}
