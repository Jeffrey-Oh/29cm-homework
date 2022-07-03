package kr.co._29cm.homework.domain.items;

import kr.co._29cm.homework.common.exception.SoldOutException;
import kr.co._29cm.homework.domain.order.OrderItemsInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsServiceImpl implements ItemsService {

    private final ItemsReader itemsReader;
    private final ItemsInfoMapper itemsInfoMapper;

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
        List<ItemsInfo.ItemsInfoAll> itemsInfoAllList = new ArrayList<>();
        itemsList.forEach(items -> itemsInfoAllList.add(itemsInfoMapper.of(items)));
        return itemsInfoAllList;
    }

    /**
     * 판매 가능한 재고가 있는지 수량 전체 확인
     */
    @Override
    public void checkAffordStock(List<OrderItemsInfo.OrderItems> orderItemsList) {
        AtomicBoolean result = new AtomicBoolean(true);

        orderItemsList.forEach(orderItems -> {
            Items items = itemsReader.getItems(orderItems.getItemToken());

            if (items.getStock() - orderItems.getOrderCount() < 0)
                result.set(false);
        });

        if (!result.get()) throw new SoldOutException();
    }

    /**
     * 판매 후 재고 수량 변경
     */
    @Override
    public void changeStockAfterSell(List<OrderItemsInfo.OrderItems> orderItemsList) {
        for (OrderItemsInfo.OrderItems orderItemsInfoAll : orderItemsList) {
            Items items = itemsReader.getItems(orderItemsInfoAll.getItemToken());
            items.changeStockAfterSell(orderItemsInfoAll.getOrderCount());
        }
    }

}
