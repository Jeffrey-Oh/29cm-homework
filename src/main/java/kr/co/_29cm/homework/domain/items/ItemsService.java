package kr.co._29cm.homework.domain.items;

import kr.co._29cm.homework.domain.order.OrderItems;

import java.util.List;

public interface ItemsService {

    ItemsInfo.ItemsInfoAll getItems(String itemToken);
    List<ItemsInfo.ItemsInfoAll> getItemsAll();
    void checkAffordStock(List<OrderItems> orderItemsList);
    void changeStockAfterSell(List<OrderItems> orderItemsList);

}
