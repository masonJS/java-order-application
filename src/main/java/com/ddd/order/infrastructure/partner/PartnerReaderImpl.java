package com.ddd.order.infrastructure.partner;


import com.ddd.order.common.exception.EntityNotFoundException;
import com.ddd.order.domain.partner.Partner;
import com.ddd.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReaderImpl implements PartnerReader {
    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository
                .findByPartnerToken(partnerToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
