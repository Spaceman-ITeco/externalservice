package teach.iteco.ru.externalservice.validation;

import teach.iteco.ru.externalservice.repository.CurrencyRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String>  {
    private final CurrencyRepository currencyRepository;

    public CurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return currencyRepository.findByName(value) != null;
    }
}
