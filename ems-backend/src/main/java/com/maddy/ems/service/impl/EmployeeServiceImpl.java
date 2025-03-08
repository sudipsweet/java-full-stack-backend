package com.maddy.ems.service.impl;

import com.maddy.ems.dto.EmployeeDto;
import com.maddy.ems.entity.Employee;
import com.maddy.ems.mapper.EmployeeMapper;
import com.maddy.ems.repository.EmployeeRepository;
import com.maddy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    /**
     * @return EmployeeDto
     */
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    /**
     * @return EmployeeDto
     */
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employeeById = employeeRepository.findById(employeeId)
                .orElseThrow(()-> {
                    return new RuntimeException("Employee not found with id: "+employeeId);
                });

        return EmployeeMapper.mapToEmployeeDto(employeeById);
    }

    /**
     * @allEmployee
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());

        Employee updateEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployee);
    }

    /**
     * @param employeeId
     */
    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        employeeRepository.deleteById(employeeId);
    }
}
