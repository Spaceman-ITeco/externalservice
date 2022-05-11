package teach.iteco.ru.externalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teach.iteco.ru.externalservice.model.entity.CurrencyEntity;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    CurrencyEntity findByName(String name);
}
