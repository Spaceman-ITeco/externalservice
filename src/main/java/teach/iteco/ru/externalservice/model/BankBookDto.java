package teach.iteco.ru.externalservice.model;

import lombok.Builder;
import lombok.Data;
import teach.iteco.ru.externalservice.validation.Create;
import teach.iteco.ru.externalservice.validation.Currency;
import teach.iteco.ru.externalservice.validation.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotBlank(message = "Not blank!")
    private String number;
    @Min(value = 0L)
    private BigDecimal amount;
    @Currency
    private String currency;

}

