package ru.gb.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // то же самое что и Component
public class TimesheetService {
    @Autowired
    private final TimesheetRepository repository;

    public TimesheetService(TimesheetRepository repository) {
        this.repository = repository;
    }

    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return repository.getById(id);
    }

    public List<Timesheet> getAll() {
        return repository.getAll();
    }

    public Timesheet createTimesheet(Timesheet timesheet) {

//        if (projectRepository.find(timesheet.getProjectId()).isExists()) {
//            return repository.create(timesheet);
//        }
        return repository.createTimesheet(timesheet);
    }


    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Timesheet> filterByDate(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        return repository.filterByDate(createdAtBefore, createdAtAfter);
    }
}
