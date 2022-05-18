package teach.iteco.ru.externalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teach.iteco.ru.externalservice.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByName(String name);
}
