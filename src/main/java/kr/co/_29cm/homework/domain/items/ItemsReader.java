package kr.co._29cm.homework.domain.items;

import java.util.List;

public interface ItemsReader {

    Items getItems(String itemToken);
    List<Items> getItemsAll();

}
