package kr.co._29cm.homework.domain.items;

import lombok.Getter;

public class ItemsInfo {

    @Getter
    public static class ItemsInfoAll {
        private final String itemToken;
        private final String itemNo;
        private final String itemName;
        private final Long price;
        private final int stock;
        private final Items.Status status;

        public ItemsInfoAll(Items items) {
            this.itemToken = items.getItemToken();
            this.itemNo = items.getItemNo();
            this.itemName = items.getItemName();
            this.price = items.getPrice();
            this.stock = items.getStock();
            this.status = items.getStatus();
        }
    }

}
