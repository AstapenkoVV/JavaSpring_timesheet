package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository) {
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Timesheet> getTimesheets(Long id){
        if(employeeRepository.findById(id).isEmpty()){
            throw new NoSuchElementException("Project with id " + id + " does not exist");
        }
        return timesheetRepository.findByProjectId(id);
    }
}
