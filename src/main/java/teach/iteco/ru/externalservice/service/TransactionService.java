package teach.iteco.ru.externalservice.service;

import teach.iteco.ru.externalservice.model.BankBookDto;
import teach.iteco.ru.externalservice.model.UserDto;

import java.math.BigDecimal;

public interface TransactionService {
    Boolean transferBetweenBankBook(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount);
    Boolean transferBetweenUser(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency);

}
