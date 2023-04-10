package com.ddd.order.infrastructure.item.optiongroup;


import com.ddd.order.domain.item.optionGroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, Long> {
}
