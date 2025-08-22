package com.re_teraction.backend.application.project;

import com.re_teraction.backend.application.project.dto.event.CreateCategoryEvent;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectCategoryEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishProjectCategoryCreated(Long projectId, Set<String> categories) {
        CreateCategoryEvent event = new CreateCategoryEvent(projectId, categories);
        applicationEventPublisher.publishEvent(event);
    }
}
