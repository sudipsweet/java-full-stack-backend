package com.maddy.ems.service;

import com.maddy.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

     EmployeeDto addEmployee(EmployeeDto employee);

     EmployeeDto getEmployeeById(Long employeeId);

     List<EmployeeDto> getAllEmployees();

     EmployeeDto updateEmployee(Long employeeId, EmployeeDto employee);

     void deleteEmployee(Long employeeId);
}
