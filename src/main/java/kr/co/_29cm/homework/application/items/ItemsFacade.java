package kr.co._29cm.homework.application.items;

import kr.co._29cm.homework.domain.items.ItemsInfo;
import kr.co._29cm.homework.domain.items.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsFacade {

    private final ItemsService itemsService;

    /**
     * 상품 단일 조회
     */
    public ItemsInfo.ItemsInfoAll getItems(String itemToken) {
        return itemsService.getItems(itemToken);
    }

    /**
     * 상품 목록 조회
     */
    public List<ItemsInfo.ItemsInfoAll> getItemsAll() {
        return itemsService.getItemsAll();
    }

}
