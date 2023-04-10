package com.ddd.order.domain.item;

public interface ItemService {
    String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken);
}
