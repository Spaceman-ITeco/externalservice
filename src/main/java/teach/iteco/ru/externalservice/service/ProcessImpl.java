package teach.iteco.ru.externalservice.service;

import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.ExternalInfo;

//@Component
public class ProcessImpl implements Process{
    @Override
    public boolean run(ExternalInfo externalInfo) {
        return false;
    }
}
