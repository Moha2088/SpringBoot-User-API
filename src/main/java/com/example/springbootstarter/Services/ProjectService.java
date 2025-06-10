package com.example.springbootstarter.Services;

import com.example.springbootstarter.CQRS.Commands.Project.CreateProjectCommand;
import com.example.springbootstarter.CQRS.Queries.Project.GetProjectByIdQuery;
import com.example.springbootstarter.DTOS.User.ProjectGetDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ProjectService {
    Optional<ProjectGetDto> CreateProject(CreateProjectCommand command);

    Optional<ProjectGetDto> GetProjectById(GetProjectByIdQuery query);
}