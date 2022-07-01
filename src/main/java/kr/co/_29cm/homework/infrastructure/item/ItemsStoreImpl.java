package kr.co._29cm.homework.infrastructure.item;

import kr.co._29cm.homework.domain.items.Items;
import kr.co._29cm.homework.domain.items.ItemsStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemsStoreImpl implements ItemsStore {

    private final ItemsRepository itemsRepository;

    @Override
    public Items store(Items items) {
        return itemsRepository.save(items);
    }

}
