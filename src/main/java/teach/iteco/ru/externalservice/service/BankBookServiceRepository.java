package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.BankBookDto;
import teach.iteco.ru.externalservice.model.entity.BankBookEntity;
import teach.iteco.ru.externalservice.repository.BankBookRepository;

import java.util.List;

@Slf4j
@Component
@Primary
public class BankBookServiceRepository implements BankBookService{

    private final BankBookRepository bankBookRepository;

    public BankBookServiceRepository(BankBookRepository bankBookRepository) {
        this.bankBookRepository = bankBookRepository;
    }

    @Override
    public BankBookDto findById(Integer id) {
       /* BankBookEntity bankBookEntity= bankBookRepository.findById(id);
      log.info("BankBook from repo: {}", bankBookEntity);
        return mapToDto(bankBookEntity);*/
        return null;
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {
        return null;
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        return null;
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {
     bankBookRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Integer userId) {

    }


    private BankBookDto mapToDto(BankBookEntity bankBookEntity) {
        return BankBookDto.builder()
                .id(bankBookEntity.getId())
                .number(bankBookEntity.getNumber())
                .amount(bankBookEntity.getAmount())
                .currency(bankBookEntity.getCurrency())
                .userId(bankBookEntity.getId())
                .build();
    }

    private BankBookEntity mapToEntity(BankBookDto bankBookDto) {
        return BankBookEntity.builder()
                .id(bankBookDto.getId())
                .number(bankBookDto.getNumber())
                .amount(bankBookDto.getAmount())
                .currency(bankBookDto.getCurrency())
                // .user(bankBookDto.getId())
                .build();
    }
}
