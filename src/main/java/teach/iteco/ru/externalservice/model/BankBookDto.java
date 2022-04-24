package teach.iteco.ru.externalservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BankBookDto {

    private Integer id;
    private Integer userId;
    private String number;
    private BigDecimal amount;
    private String currency;

}

