package com.ddd.order.application.partner;

import com.ddd.order.domain.notification.NotificationService;
import com.ddd.order.domain.partner.PartnerCommand;
import com.ddd.order.domain.partner.PartnerInfo;
import com.ddd.order.domain.partner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    public PartnerInfo registerPartner(PartnerCommand partnerCommand) {
        val partnerInfo = partnerService.registerPartner(partnerCommand);
        notificationService.sendEmail(partnerInfo.getEmail(), "회원가입 완료", "회원가입이 완료되었습니다.");
        return partnerInfo;
    }
}
