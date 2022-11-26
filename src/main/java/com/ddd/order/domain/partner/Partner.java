package com.ddd.order.domain.partner;

import com.ddd.order.common.util.TokenGenerator;
import com.ddd.order.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
public class Partner extends BaseEntity {
    private static final String PREFIX_PARTNER = "ptn_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");
        private final String description;
    }

    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        if(StringUtils.isEmpty(partnerName)) throw new RuntimeException("empty partnerName");
        if(StringUtils.isEmpty(businessNo)) throw new RuntimeException("empty businessNo");
        if(StringUtils.isEmpty(email)) throw new RuntimeException("empty email");

        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = Status.ENABLE;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
