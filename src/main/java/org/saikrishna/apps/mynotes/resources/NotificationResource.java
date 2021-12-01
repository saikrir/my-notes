package org.saikrishna.apps.mynotes.resources;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class NotificationResource {

    @NotEmpty(message = "Notification Message cannot be empty")
    private String message;
    private String sid;
}
