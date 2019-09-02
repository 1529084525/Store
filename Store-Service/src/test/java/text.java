import interfaces.collect.CollectInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Collect;
import service.collect.CollectService;

public class text {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CollectInterface aa=(CollectService) applicationContext.getBean("CollectService");
        Collect co=new Collect();



    }

}
