package com.ddd.order.domain.item.option;


import com.ddd.order.common.exception.InvalidParamException;
import com.ddd.order.domain.BaseEntity;
import com.ddd.order.domain.item.optionGroup.ItemOptionGroup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_options")
public class ItemOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroup itemOptionGroup;
    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public ItemOption(ItemOptionGroup itemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        if (itemOptionGroup == null) throw new InvalidParamException("empty ItemOption.itemOptionGroup");
        if (ordering == null) throw new InvalidParamException("empty ItemOption.ordering");
        if (StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("empty ItemOption.itemOptionName");
        if (itemOptionPrice == null) throw new InvalidParamException("empty ItemOption.itemOptionPrice");

        this.itemOptionGroup = itemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }

}
