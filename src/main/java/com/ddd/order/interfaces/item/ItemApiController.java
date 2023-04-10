package com.ddd.order.interfaces.item;


import com.ddd.order.application.item.ItemFacade;
import com.ddd.order.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemApiController {
    private final ItemFacade itemFacade;
    private final ItemDtoMapper itemDtoMapper;

    @PostMapping
    public CommonResponse registerItem(@RequestBody @Valid ItemDto.RegisterItemRequest request) {
        var partnerToken = request.getPartnerToken();
        var itemCommand = itemDtoMapper.of(request);

        var itemToken = itemFacade.registerItem(itemCommand, partnerToken);

        var response = itemDtoMapper.of(itemToken);
        return CommonResponse.success(response);
    }

}
