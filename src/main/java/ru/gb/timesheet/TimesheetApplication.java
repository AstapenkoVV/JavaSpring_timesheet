package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

		TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
		ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
		EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);

		for (int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setFirstName("EmployeeName #" + i);
			employeeRepo.save(employee);
		}

		for (int i = 1; i <= 5; i++) {
			Project project = new Project();
			project.setProjectName("Project #" + i);
			projectRepo.save(project);
		}

		LocalDate createdAt = LocalDate.now();
		for (int i = 0; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));

			timesheetRepo.save(timesheet);
		}

	}
}
