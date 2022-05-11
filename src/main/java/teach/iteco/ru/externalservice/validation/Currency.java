package teach.iteco.ru.externalservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CurrencyValidator.class)
@Documented
public @interface Currency {

    String message() default "Некорректная валюта!"; //ключ ValidationMessages.properties

    Class<?>[] groups() default {}; //группа проверки

    Class<? extends Payload>[] payload() default {}; //полезная нагрузка
}