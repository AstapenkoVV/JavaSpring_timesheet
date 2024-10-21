package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    // select * from timesheet where projectId = $1
    // List<Timesheet> findByProjectId(Long projectId);

    // select * from timesheet where projectId = $1
    // order by created_at desc
    // jql - java query language
//    @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjectId(Long projectId);

    // select * from timesheet where created_at > $1 and created_at < $2
    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);

    // select * from timesheet where projectId = $1
    // Note: сломается, если в БД результат выдает больше одного значения
    // Optional<Timesheet> findByProjectId(Long projectId;

//    default List<Timesheet> findByCreatedAtBetweenUnsafe(LocalDate min, LocalDate max) {
//        if (min == null && max == null) {
//            return findAll();
//        } else if (min == null) {
//            return findByCreatedAtLessThen(max);
//        }
//        //........
//    }

}
