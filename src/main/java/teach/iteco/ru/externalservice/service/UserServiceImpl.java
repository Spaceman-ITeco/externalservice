package teach.iteco.ru.externalservice.service;

import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.UserDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserServiceImpl implements UserService{
    private final Map<Integer, UserDto> userDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        int id = sequenceId.getAndIncrement();
        userDtoMap.put(id, UserDto.builder().id(id).email("email@email.com").name("NAME").build());
    }

    @Override
    public List<UserDto> getAll() {
        return new ArrayList<>(userDtoMap.values());
    }

    @Override
    public UserDto getById(Integer id) {
        return userDtoMap.get(id);
    }

    @Override
    public UserDto create(UserDto userDto) {
        int id = sequenceId.getAndIncrement();
        userDto.setId(id);
        userDtoMap.put(id, userDto);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserDto userDtoFromMap = userDtoMap.get(userDto.getId());
        if (userDtoFromMap == null) {
            return null;
        } else {
            userDtoMap.put(userDto.getId(), userDto);
            return userDto;
        }
    }

    @Override
    public void delete(Integer id) {
        userDtoMap.remove(id);
    }
}
