package com.ddd.order.infrastructure.item;

import com.ddd.order.domain.item.Item;
import com.ddd.order.domain.item.ItemCommand;
import com.ddd.order.domain.item.ItemOptionSeriesFactory;
import com.ddd.order.domain.item.option.ItemOptionStore;
import com.ddd.order.domain.item.optionGroup.ItemOptionGroup;
import com.ddd.order.domain.item.optionGroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        var itemOptionGroupList = command.getItemOptionGroupList();

        if (CollectionUtils.isEmpty(itemOptionGroupList)) {
            return Collections.emptyList();
        }

        return itemOptionGroupList
                .stream()
                .map(requestItemOptionGroup -> {
                    var itemOptionGroup = itemOptionGroupStore.store(requestItemOptionGroup.toEntity(item));

                    requestItemOptionGroup.getItemOptionList().forEach(requestItemOption -> {
                        itemOptionStore.store(requestItemOption.toEntity(itemOptionGroup));
                    });

                    return itemOptionGroup;
                })
                .collect(Collectors.toList());

    }
}
