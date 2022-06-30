package kr.co._29cm.homework.domain.items;

import java.util.List;

public interface ItemsInfoAllFactory {

    List<ItemsInfo.ItemsInfoAll> itemsInfoAllBuilder(List<Items> itemsList);

}
