package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Repository  // @Component для классов работающих с данными
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> timesheets = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        // select * from timesheets where id = $id
        return timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Timesheet> getAll() {
        return List.copyOf(timesheets);
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        timesheet.setId(sequence++);
        timesheet.setCreatedAt(LocalDate.now());
        timesheets.add(timesheet);
        return timesheet;
    }

    public void delete(Long id) {
        timesheets.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(timesheets::remove); // если нет - иногда посылают 404 Not Found
    }

    public List<Timesheet> filterByDate(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        Predicate<Timesheet> filter = it -> true;
        if (Objects.nonNull(createdAtBefore)) {
            filter = filter.and(it -> it.getCreatedAt().isBefore(ChronoLocalDate.from(createdAtBefore)));
        }
        if (Objects.nonNull(createdAtAfter)) {
            filter = filter.and(it -> it.getCreatedAt().isAfter(ChronoLocalDate.from(createdAtAfter)));
        }
        return timesheets.stream()
                .filter(filter)
                .toList();
    }
}
