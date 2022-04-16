package teach.iteco.ru.externalservice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import teach.iteco.ru.externalservice.model.ExternalInfo;
import teach.iteco.ru.externalservice.service.ExternalServiceImpl;
import teach.iteco.ru.externalservice.service.Flow;


@EnableAspectJAutoProxy
@Slf4j
@ComponentScan
@PropertySource("classpath:application.properties")
public class ExternalServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(ExternalServiceApplication.class);
			homeworkOne((AnnotationConfigApplicationContext) applicationContext);



	}

	private static void homeworkOne(AnnotationConfigApplicationContext applicationContext) {
		Flow flow = applicationContext.getBean(Flow.class);
		flow.run(1);
		flow.run(2);
		flow.run(3);
		flow.run(4);
		flow.run(5);
		applicationContext.close();
	}
}



