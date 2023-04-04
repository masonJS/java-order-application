package com.ddd.order.domain.notification;

public interface NotificationService {
    void sendEmail(String email, String title, String description);
    void sendKakao(String phoneNumber, String description);
    void sendSms(String phoneNumber, String description);
}
