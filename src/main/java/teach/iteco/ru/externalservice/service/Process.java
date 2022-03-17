package teach.iteco.ru.externalservice.service;

import teach.iteco.ru.externalservice.model.ExternalInfo;

public interface Process {
    boolean run(ExternalInfo externalInfo);
}
