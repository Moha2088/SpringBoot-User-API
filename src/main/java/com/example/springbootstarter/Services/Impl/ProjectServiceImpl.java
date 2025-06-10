package com.example.springbootstarter.Services.Impl;

import com.example.springbootstarter.CQRS.Commands.Project.CreateProjectCommand;
import com.example.springbootstarter.DTOS.Project.ProjectGetDto;
import com.example.springbootstarter.Repositories.ProjectRepository;
import com.example.springbootstarter.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<ProjectGetDto> CreateProject(CreateProjectCommand command){

    }
}
