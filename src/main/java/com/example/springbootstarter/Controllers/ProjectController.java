package com.example.springbootstarter.Controllers;

import com.example.springbootstarter.CQRS.Commands.Project.CreateProjectCommand;
import com.example.springbootstarter.CQRS.Commands.Project.DeleteProjectCommand;
import com.example.springbootstarter.CQRS.Queries.Project.GetProjectByIdQuery;
import com.example.springbootstarter.Controllers.Exceptions.ProjectNotFoundException;
import com.example.springbootstarter.DTOS.Project.ProjectDto;
import com.example.springbootstarter.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDto> CreateProject(@RequestBody CreateProjectCommand command, HttpServletRequest req) {

        ProjectDto result = projectService.CreateProject(command);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProjectDto>> GetProjectById(@PathVariable Long id) throws ProjectNotFoundException {
        GetProjectByIdQuery query = new GetProjectByIdQuery(id);
        Optional<ProjectDto> result = projectService.GetProjectById(query);
        if (result.isEmpty()) {
            throw new ProjectNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Long>> DeleteProjectById(@PathVariable Long id) throws ProjectNotFoundException {
        DeleteProjectCommand command = new DeleteProjectCommand(id);
        Optional<Long> result = projectService.DeleteProject(command);
        if (result.isPresent()) {
            throw new ProjectNotFoundException(id);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
