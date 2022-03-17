package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.ExternalInfo;

@Component
@Lazy
@Slf4j
public class ExternalInfoProcess implements Process{
    @Value("${id-not-process}")
    private Integer id;

public ExternalInfoProcess() {}

    @Override
    public boolean run(ExternalInfo externalInfo) {
        if (id.equals(externalInfo.getId())) {
            log.info("Process not need: {}", externalInfo);
            return false;
        }
        log.info("Process with: {}", externalInfo);
        return true;
    }
}
