package teach.iteco.ru.externalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teach.iteco.ru.externalservice.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
