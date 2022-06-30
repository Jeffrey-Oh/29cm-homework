package kr.co._29cm.homework.domain.items;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemsInfoAllFactoryImpl implements ItemsInfoAllFactory {

    @Override
    public List<ItemsInfo.ItemsInfoAll> itemsInfoAllBuilder(List<Items> itemsList) {
        return itemsList.stream().map(ItemsInfo.ItemsInfoAll::new).collect(Collectors.toList());
    }

}
