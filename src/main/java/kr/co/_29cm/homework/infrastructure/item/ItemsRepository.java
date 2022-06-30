package kr.co._29cm.homework.infrastructure.item;

import kr.co._29cm.homework.domain.items.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    Optional<Items> findByItemToken(String itemToken);

}
