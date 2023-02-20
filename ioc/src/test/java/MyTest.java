import com.blue.framework.ioc.bean.BeanDefinition;
import com.blue.framework.ioc.bean.ConstructorArg;
import com.blue.framework.ioc.bean.PropertyArg;
import com.blue.framework.ioc.core.*;
import com.blue.pojo.Mouth;
import com.blue.pojo.Person;
import com.blue.pojo.Robot;
import com.blue.pojo.Work;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: ioc
 * @package: PACKAGE_NAME
 * @className: Test
 * @author: BlueOfflaner
 * @description: TODO
 * @date: 2023/2/15 18:52
 */
public class MyTest {

    @Test
    //这个test是将AbstractApplicationContext中的 registerBean方法改为 public执行的, 现已改回protected
    public void test1() throws Exception {

        //List<PropertyArg> list = new ArrayList<>();
        //PropertyArg arg = new PropertyArg();
        //arg.setName("name");
        //arg.setTypeName("String");
        //arg.setValue("zhangsan");
        //list.add(arg);
        //
        //AbstractApplicationContext beanFactory = new AbstractApplicationContext();
        //BeanDefinition beanDefinitionPerson = new BeanDefinition();
        //beanDefinitionPerson.setName("person");
        //beanDefinitionPerson.setClassName("com.blue.pojo.Person");
        //beanDefinitionPerson.setPropertyArgs(list);
        //
        //ConstructorArg constructorArg = new ConstructorArg();
        //constructorArg.setIndex(0);
        //constructorArg.setName("age");
        //constructorArg.setValue(18);
        //List<ConstructorArg> constructorArgs = new ArrayList<>();
        //constructorArgs.add(constructorArg);
        //beanDefinitionPerson.setConstructorArgs(constructorArgs);
        //
        //BeanDefinition beanDefinitionWork = new BeanDefinition();
        //beanDefinitionWork.setName("work");
        //beanDefinitionWork.setClassName("com.blue.pojo.Work");
        //beanFactory.registerBean("person", beanDefinitionPerson);
        //beanFactory.registerBean("work", beanDefinitionWork);
        //
        //Person person = (Person) beanFactory.getBean("person");
        //System.out.println(person);
    }

    @Test
    public void JsonApplicationTest() {
        JsonApplicationContext jsonApplicationContext = new JsonApplicationContext("application.json");
        jsonApplicationContext.init();
        Robot robot = null;
        try {
            robot = (Robot) jsonApplicationContext.getBean("robot");
        } catch (Exception e) {
            e.printStackTrace();
        }
        robot.show();
    }

    @Test
    public void AnnotationApplicationTest() throws Exception {
        ApplicationContext applicationContext = new AnnotationApplicationContext("com.blue.pojo");
        applicationContext.init();
        Person person = (Person) applicationContext.getBean("person");
        person.work();

        Robot robot = (Robot) applicationContext.getBean("robot");
        robot.show();

        Mouth mouth = (Mouth) applicationContext.getBean("com.blue.pojo.Mouth");
        mouth.speak();
    }
}
