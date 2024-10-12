package ru.gb.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/{projectId}/timesheets")
public class TimesheetController {

    // GET - получить - не содержит тела (body)
    // POST - создать
    // PUT - изменить целиком
    // PATCH - изменить выборочно
    // DELETE - удалить

    //@GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    //@DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
    //@PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору
    @Autowired
    private final TimesheetService service;
    @Autowired
    private final ProjectController projectController;

    public TimesheetController(TimesheetService service, ProjectController projectController) {
        this.service = service;
        this.projectController = projectController;
    }

    @GetMapping("/{id}") // получить все
    public ResponseEntity<Timesheet> getTimesheet(@PathVariable("id") Long id) {
        Optional<Timesheet> ts = service.getById(id);
        if (ts.isPresent()) {
//            return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping // получить все
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Timesheet>> filterByDate(@RequestParam(required = false) LocalDate createdAtBefore,
                                                        @RequestParam(required = false) LocalDate createdAtAfter) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.filterByDate(createdAtBefore, createdAtAfter));
    }

    // Locations: /timesheet/sequence
    // 201 Created
    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheet, @PathVariable("projectId") Long projectId) {
        if (projectController.getProjectById(projectId).getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            timesheet.setProjectId(projectId);
            timesheet = service.createTimesheet(timesheet);
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // если нет - иногда посылают 404 Not Found
    // 204 - No Content
//    @DeleteMapping("/timesheets/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
