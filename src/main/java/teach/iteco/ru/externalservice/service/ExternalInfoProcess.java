package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.ExternalInfo;
import teach.iteco.ru.externalservice.model.annotation.CheckRequest;

@Component
@Lazy
@Slf4j
public class ExternalInfoProcess implements Process{
    @Value("${id-not-process}")
    private Integer id;

public ExternalInfoProcess() {}

    @Override
    @CheckRequest
    public boolean run(ExternalInfo externalInfo) {
        {
          try {
                if (id.equals(externalInfo.getId()))
                {  log.info("Process not need: {}", externalInfo);
             throw new ProcessException("ExternalInfo.getId == " + externalInfo.getId()  + " not-process",externalInfo.getId());}
          } catch (ProcessException exception)
          {System.out.println("ExternalInfo.getId == "+ externalInfo.getId() + " not-process");}
        }
        log.info("Process with: {}", externalInfo);
        return true;
    }
}
