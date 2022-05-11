package teach.iteco.ru.externalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teach.iteco.ru.externalservice.model.entity.BankBookEntity;

public interface BankBookRepository extends JpaRepository<BankBookEntity, Integer> {


}

