package ru.gb.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getById(Long id) {
        return projectRepository.getById(id);
    }

    public List<Project> getAll() {
        return projectRepository.getAllProjects();
    }

    public Project createProject(Project project) {
        return projectRepository.createProject(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
