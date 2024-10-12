package ru.gb.timesheet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ProjectRepository {

    private static Long sequence = 1L;
    private final List<Project> projects = new ArrayList<>();

    public Optional<Project> getById(Long id) {
        return projects.stream()
                .filter(it -> Objects.equals(it.getProjectId(), id))
                .findFirst();
    }

    public List<Project> getAllProjects() {
        return List.copyOf(projects);
    }

    public Project createProject(Project project) {
        project.setProjectId(sequence++);
        projects.add(project);
        return project;
    }

    public void deleteById(Long id) {
        projects.stream()
                .filter(it -> Objects.equals(it.getProjectId(), id))
                .findFirst()
                .ifPresent(projects::remove);
    }
}
