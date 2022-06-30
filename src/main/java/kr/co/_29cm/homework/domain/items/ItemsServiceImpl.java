package kr.co._29cm.homework.domain.items;

import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.order.OrderItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService {

    private final ItemsReader itemsReader;
    private final ItemsInfoAllFactory itemsInfoAllFactory;

    /**
     * 상품 단일 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ItemsInfo.ItemsInfoAll getItems(String itemToken) {
        Items items = itemsReader.getItems(itemToken);
        return new ItemsInfo.ItemsInfoAll(items);
    }

    /**
     * 상품 목록 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemsInfo.ItemsInfoAll> getItemsAll() {
        List<Items> itemsList = itemsReader.getItemsAll();
        return itemsInfoAllFactory.itemsInfoAllBuilder(itemsList);
    }

    /**
     * 판매 가능한 재고가 있는지 수량 전체 확인
     */
    @Override
    @Transactional(readOnly = true)
    public void checkAffordStock(List<OrderItems> orderItemsList) {
        AtomicBoolean result = new AtomicBoolean(true);

        orderItemsList.stream().map(orderItems -> {
            Items items = itemsReader.getItems(orderItems.getItemToken());

            if (items.getStock() - orderItems.getOrderCount() < 0)
                result.set(false);

            return result.get();
        });

        if (!result.get()) throw new SoldOutException();
    }

    /**
     * 판매 후 재고 수량 변경
     */
    @Override
    @Transactional
    public void changeStockAfterSell(List<OrderItems> orderItemsList) {
        for (OrderItems orderItems : orderItemsList) {
            Items items = itemsReader.getItems(orderItems.getItemToken());
            items.changeStockAfterSell(orderItems.getOrderCount());
        }
    }

}
