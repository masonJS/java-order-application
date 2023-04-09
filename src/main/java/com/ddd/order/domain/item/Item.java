package com.ddd.order.domain.item;

import com.ddd.order.common.exception.InvalidParamException;
import com.ddd.order.common.util.TokenGenerator;
import com.ddd.order.domain.BaseEntity;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends BaseEntity {
    private static final String PREFIX_ITEM = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemToken;
    private Long partnerId;
    private String itemName;
    private Long itemPrice;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;
    }

    @Builder
    public Item(Long partnerId, String itemName, Long itemPrice) {
        if (partnerId == null) throw new InvalidParamException("empty Item.partnerId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("empty Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("empty Item.itemPrice");

        this.itemToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_ITEM);
        this.partnerId = partnerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }

    public void changePrepare() {
        this.status = Status.PREPARE;
    }

    public void changeOnSales() {
        if(this.status == Status.END_OF_SALES) {
            throw new InvalidParamException("판매종료된 상품은 판매중으로 변경할 수 없습니다");
        }
        this.status = Status.ON_SALES;
    }

    public void endOfSales() {
        this.status = Status.END_OF_SALES;
    }
}
