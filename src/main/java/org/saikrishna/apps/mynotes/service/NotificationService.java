package org.saikrishna.apps.mynotes.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class NotificationService {

    @Value("${alerts.to-phone-number}")
    private String toPhoneNumber;
    @Value("${alerts.from-phone-number}")
    private String fromPhoneNumber;

    @Value("${twilio.api-key}")
    private String apiKey;
    @Value("${twilio.account-sid}")
    private String accountSid;

    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, apiKey);
        log.info("Notification service initialized");
    }

    public String triggerNotification(String notificationText) {
        Message message = Message.creator(new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(fromPhoneNumber),
                        notificationText)
                .create();
        return message.getSid();
    }
}
