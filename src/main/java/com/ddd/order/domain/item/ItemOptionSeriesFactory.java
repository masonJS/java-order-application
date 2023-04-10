package com.ddd.order.domain.item;

import com.ddd.order.domain.item.optionGroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item);
}
