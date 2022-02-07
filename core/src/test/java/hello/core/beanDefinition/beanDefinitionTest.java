package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class beanDefinitionTest {
    AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext ac2 = new GenericXmlApplicationContext("appConfig.xml");
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac2.getBeanDefinitionNames();
        String[] beanDefinitionNames1 = ac1.getBeanDefinitionNames();


        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac2.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " beanDefinition = " + beanDefinition);
            }
        }

        for (String beanDefinitionName1 : beanDefinitionNames1) {
            BeanDefinition beanDefinition = ac1.getBeanDefinition(beanDefinitionName1);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName1 = " + beanDefinitionName1 +
                        " beanDefinition : " + beanDefinition);
            }
        }
    }
}