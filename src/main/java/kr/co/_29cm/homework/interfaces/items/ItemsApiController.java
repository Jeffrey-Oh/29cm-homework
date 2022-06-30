package kr.co._29cm.homework.interfaces.items;

import kr.co._29cm.homework.application.items.ItemsFacade;
import kr.co._29cm.homework.domain.items.ItemsInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Validated
public class ItemsApiController {

    private final ItemsFacade itemsFacade;

    @GetMapping(name = "상품 단일 조회")
    public ItemsDto.ItemsInfoAllResponse getItems(@RequestParam @NotBlank String itemToken) {
        ItemsInfo.ItemsInfoAll itemsInfoAll = itemsFacade.getItems(itemToken);
        return new ItemsDto.ItemsInfoAllResponse(itemsInfoAll);
    }

    @GetMapping(value = "/list", name = "상품 목록 조회")
    public ItemsDto.ItemsInfoAllListResponse getItemsAll() {
        List<ItemsInfo.ItemsInfoAll> itemsInfoAllList = itemsFacade.getItemsAll();
        return new ItemsDto.ItemsInfoAllListResponse(itemsInfoAllList);
    }

}
