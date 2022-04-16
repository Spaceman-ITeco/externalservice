package teach.iteco.ru.externalservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import teach.iteco.ru.externalservice.model.ExternalInfo;
import teach.iteco.ru.externalservice.model.annotation.CacheResult;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class ExternalServiceImpl implements ExternalService{


    private final Map<Integer, ExternalInfo> externalInfoData = new HashMap<>();

    @Value("${id-not-process}")
    private Integer id;


    public ExternalServiceImpl() {
    }

    @PostConstruct
    public void init()
    {   externalInfoData.put(1, new ExternalInfo(1,null));
        externalInfoData.put(2, new ExternalInfo(2,"hasInfo"));
        externalInfoData.put(3, new ExternalInfo(3,"info"));
        externalInfoData.put(4, new ExternalInfo(4,"information"));};

    @PreDestroy
    public void destroy()
    {
        log.info("Map before: {}", externalInfoData);
        externalInfoData.clear();
        log.info("Map after: {}", externalInfoData);
    }


   @CacheResult
    public ExternalInfo getExternalInfo(Integer id) {
       ExternalInfo externalInfo = externalInfoData.get(id);
       if (externalInfo==null)
       try {
               throw new RuntimeException(id + " In Map not found!");
       } catch (NullPointerException exception) {
           System.out.println(id + " In Map not found!");
       }
       return externalInfoData.get(id);
   }

}
