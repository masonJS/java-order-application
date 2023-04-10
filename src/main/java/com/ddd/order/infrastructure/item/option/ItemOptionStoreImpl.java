package com.ddd.order.infrastructure.item.option;

import com.ddd.order.domain.item.option.ItemOption;
import com.ddd.order.domain.item.option.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionStoreImpl implements ItemOptionStore {

    private final ItemOptionRepository itemOptionRepository;

    @Override
    public ItemOption store(ItemOption itemOption) {
        return itemOptionRepository.save(itemOption);
    }
}