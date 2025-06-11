package com.example.springbootstarter.Services.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springbootstarter.CQRS.Commands.Project.CreateProjectCommand;
import com.example.springbootstarter.CQRS.Commands.Project.DeleteProjectCommand;
import com.example.springbootstarter.CQRS.Queries.Project.GetProjectByIdQuery;
import com.example.springbootstarter.DTOS.Project.ProjectDto;
import com.example.springbootstarter.Models.Project;
import com.example.springbootstarter.Repositories.ProjectRepository;
import com.example.springbootstarter.Services.ProjectService;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDto CreateProject(CreateProjectCommand command) {
        Project mappedProject = command.FromCommand();
        projectRepository.save(mappedProject);
        return mappedProject.ToDto();
    }

    @Override
    public Optional<ProjectDto> GetProjectById(GetProjectByIdQuery query){
        Optional<Project> project = projectRepository.findById(query.Id());
        return project.map(Project::ToDto);
    }

    @Override
    public Optional<Long> DeleteProject(DeleteProjectCommand command){
        Optional<Project> project = projectRepository.findById(command.Id());
        if(project.isEmpty()){
            return Optional.of(command.Id());
        }
        projectRepository.deleteById(command.Id());
        return Optional.empty();
    }
}