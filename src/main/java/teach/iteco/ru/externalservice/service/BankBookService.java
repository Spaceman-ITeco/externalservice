package teach.iteco.ru.externalservice.service;

import teach.iteco.ru.externalservice.model.BankBookDto;

import java.util.List;
import java.util.Set;

public interface BankBookService {
    BankBookDto findById(Integer id);
    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto create(BankBookDto bankBookDto);
    BankBookDto update(BankBookDto bankBookDto);
    void delete(Integer id);
    void deleteByUserId(Integer userId);
}
