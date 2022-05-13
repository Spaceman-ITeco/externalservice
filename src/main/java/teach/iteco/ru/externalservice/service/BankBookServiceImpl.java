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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    /*@PostConstruct
    void init() {
        bankBookDtoMap.put(1, BankBookDto.builder()
                .id(1)
                .userId(1)
                .number("num1")
                .amount(BigDecimal.TEN)
                .currency("RUB")
                .build()
        );
    }*/

    @Override
    public BankBookDto findById(Integer id) {
        BankBookDto bankBookDto = bankBookDtoMap.get(id);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Cчет не найден!");
        }
        return bankBookDto;
    }

    @Override
    public List<BankBookDto> findByUserId(Integer userId) {

        List<BankBookDto> bankBookDtos = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> userId.equals(bankBookDto.getUserId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtos)) {
            throw new BankBookNotFoundException("Для данного пользователя нет счетов");
        }

        return  bankBookDtos;
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
        BankBookDto bankBookDtoFromMap = bankBookDtoMap.get(bankBookDto.getId());
        if (bankBookDtoFromMap == null) {
            throw new BankBookNotFoundException("Лицевой счет не найден!");
        }
        if (!bankBookDtoFromMap.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberCannotBeModifiedException("Номер лицевого счета менять нельзя!");
        }
        bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);
        return bankBookDto;
    }

    @Override
    public void delete(Integer id) {
        BankBookDto bankBookDto = bankBookDtoMap.get(id);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Cчет не найден!");
        }
        bankBookDtoMap.remove(id);

    }

    @Override
    public void deleteByUserId(Integer userId) {

            List<Integer> bankBookRemoveId = bankBookDtoMap.values().stream()
                    .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
                    .map(BankBookDto::getId)
                    .collect(Collectors.toList());
            if (bankBookRemoveId.isEmpty())
            {
                throw new BankBookNotFoundException("Для данного пользователя нет счетов!");
            }

            for (Integer removeId : bankBookRemoveId) {
                bankBookDtoMap.remove(removeId);
            }
    }



}
