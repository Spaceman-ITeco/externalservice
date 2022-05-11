package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.UserDto;
import teach.iteco.ru.externalservice.model.entity.UserEntity;
import teach.iteco.ru.externalservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Primary
public class UserServiceRepository implements UserService {

    private final UserRepository userRepository;


    public UserServiceRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Integer id) {
        UserEntity userEntity = userRepository.getById(id);
        log.info("User from repo: {}", userEntity);
        return mapToDto(userEntity);
    }

    @Override
    public UserDto create(UserDto userDto) {
        UserEntity userEntity = mapToEntity(userDto);
        return mapToDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        return mapToDto(userRepository.save(userEntity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }

    private UserEntity mapToEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }

}
