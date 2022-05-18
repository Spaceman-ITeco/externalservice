package teach.iteco.ru.externalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teach.iteco.ru.externalservice.model.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
