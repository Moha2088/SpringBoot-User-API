package com.example.springbootstarter.Services;

import java.util.Optional;

import com.example.springbootstarter.CQRS.Commands.Project.CreateProjectCommand;
import com.example.springbootstarter.CQRS.Commands.Project.DeleteProjectCommand;
import com.example.springbootstarter.CQRS.Queries.Project.GetProjectByIdQuery;
import com.example.springbootstarter.DTOS.Project.ProjectDto;

public interface ProjectService {
    ProjectDto CreateProject(CreateProjectCommand command);

    Optional<ProjectDto> GetProjectById(GetProjectByIdQuery query);

    Optional<Long> DeleteProject(DeleteProjectCommand command);
}