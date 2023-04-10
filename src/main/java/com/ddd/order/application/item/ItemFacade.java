package com.ddd.order.application.item;

import com.ddd.order.domain.item.ItemCommand;
import com.ddd.order.domain.item.ItemService;
import com.ddd.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;
    private final NotificationService notificationService;

    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        var itemToken = itemService.registerItem(command, partnerToken);
        return itemToken;
    }
}
