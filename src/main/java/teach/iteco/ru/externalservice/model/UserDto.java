package teach.iteco.ru.externalservice.model;

import lombok.Builder;
import lombok.Data;
import teach.iteco.ru.externalservice.validation.Create;
import teach.iteco.ru.externalservice.validation.Email;
import teach.iteco.ru.externalservice.validation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;
    @NotBlank
    private String name;
    @Email
    private String email;

}