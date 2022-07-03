package kr.co._29cm.homework.domain.items;

import kr.co._29cm.homework.domain.order.OrderItemsInfo;

import java.util.List;

public interface ItemsService {

    ItemsInfo.ItemsInfoAll getItems(String itemToken);
    List<ItemsInfo.ItemsInfoAll> getItemsAll();
    void checkAffordStock(List<OrderItemsInfo.OrderItems> orderItemsInfoAllList);
    void changeStockAfterSell(List<OrderItemsInfo.OrderItems> orderItemsInfoAllList);

}
