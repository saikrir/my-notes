package org.saikrishna.apps.mynotes.controllers;

import lombok.RequiredArgsConstructor;
import org.saikrishna.apps.mynotes.resources.NotificationResource;
import org.saikrishna.apps.mynotes.service.NotificationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/notifications/")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(value = "push", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationResource> notify(@RequestBody @Valid NotificationResource notificationResource) {
        String notificationResponse = notificationService.triggerNotification(notificationResource.getMessage());
        notificationResource.setSid(notificationResponse);
        return ResponseEntity.ok(notificationResource);
    }
}
