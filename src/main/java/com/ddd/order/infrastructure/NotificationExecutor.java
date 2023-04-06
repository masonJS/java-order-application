package com.ddd.order.infrastructure;

import com.ddd.order.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class NotificationExecutor implements NotificationService {
    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("send email to {} with title {} and description {}", email, title, description);
    }

    @Override
    public void sendKakao(String phoneNumber, String description) {
        log.info("send kakao to {} with description {}", phoneNumber, description);
    }

    @Override
    public void sendSms(String phoneNumber, String description) {
        log.info("send sms to {} with description {}", phoneNumber, description);

    }
}
