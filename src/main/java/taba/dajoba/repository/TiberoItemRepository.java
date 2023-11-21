package taba.dajoba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taba.dajoba.entity.TiberoItem;

public interface TiberoItemRepository extends JpaRepository<TiberoItem, Long> {
}

