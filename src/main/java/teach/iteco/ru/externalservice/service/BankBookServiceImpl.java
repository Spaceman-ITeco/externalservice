package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import teach.iteco.ru.externalservice.mapper.BankBookMapper;
import teach.iteco.ru.externalservice.model.BankBookDto;
import teach.iteco.ru.externalservice.model.entity.BankBookEntity;
import teach.iteco.ru.externalservice.model.entity.CurrencyEntity;
import teach.iteco.ru.externalservice.model.exception.BankBookNotFoundException;
import teach.iteco.ru.externalservice.model.exception.BankBookNumberCannotBeModifiedException;
import teach.iteco.ru.externalservice.model.exception.BankBookWithCurrencyAlreadyHaveException;
import teach.iteco.ru.externalservice.repository.BankBookRepository;
import teach.iteco.ru.externalservice.repository.CurrencyRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BankBookServiceImpl implements BankBookService{

    private final BankBookRepository bankBookRepository;
    private final CurrencyRepository currencyRepository;
    private final BankBookMapper bankBookMapper;

    public BankBookServiceImpl(BankBookRepository bankBookRepository, CurrencyRepository currencyRepository,
                               BankBookMapper bankBookMapper) {
        this.bankBookRepository = bankBookRepository;
        this.currencyRepository = currencyRepository;
        this.bankBookMapper = bankBookMapper;
    }
    private final Map<Integer, BankBookDto> bankBookDtoMap = new HashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @Override
    public BankBookDto findById(Integer id) {
        return bankBookRepository.findById(id)
                .map(bankBookMapper::mapToDto)
                .orElseThrow(() -> new BankBookNotFoundException("Cчет не найден!"));
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {

        List<BankBookDto> bankBookDtos = bankBookRepository.findAllByUserId(userId).stream()
                .map(bankBookMapper::mapToDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }
        return bankBookDtos;
    }

    @Override
    public BankBookDto create(BankBookDto bankBookDto) {
        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());
        Optional<BankBookEntity> bankBookEntityOpt = bankBookRepository.findByUserIdAndNumberAndCurrency(
                bankBookDto.getUserId(),
                bankBookDto.getNumber(),
                currency
        );
        if (bankBookEntityOpt.isPresent()) {
            throw new BankBookWithCurrencyAlreadyHaveException("Счет с данной валютой уже имеется!");
        }
        BankBookEntity bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public BankBookDto update(BankBookDto bankBookDto) {
        BankBookEntity bankBookEntity = bankBookRepository.findById(bankBookDto.getId())
                .orElseThrow(() -> new BankBookNotFoundException("Лицевой счет не найден!"));

        if (!bankBookEntity.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }

        CurrencyEntity currency = currencyRepository.findByName(bankBookDto.getCurrency());

        bankBookEntity = bankBookMapper.mapToEntity(bankBookDto);
        bankBookEntity.setCurrency(currency);
        return bankBookMapper.mapToDto(
                bankBookRepository.save(bankBookEntity)
        );
    }

    @Override
    public void delete(Integer id) {
         if (!bankBookRepository.existsById(id)) {
            throw new BankBookNotFoundException("Cчет не найден!");
        }
        bankBookRepository.deleteById(id);

    }

    @Override
    public void deleteByUserId(Integer userId) {

        if (bankBookRepository.findAllByUserId(userId).isEmpty())
        {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов!");
        }
        else

           bankBookRepository.deleteAllByUserId(userId);
    }



}
