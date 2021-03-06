import com.dingcheng.confirms.publish.RabbitTemplatePublishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestRabbitTemplate {
    @Autowired
    private RabbitTemplatePublishService publishService;

    private static String exChange = "DIRECT_EX";

    @Test
    public void test12() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message:" + message);
        int i = 0;
        while (i < 500000) {
            //exchange,queue 都正确,confirm被回调, ack=true
//            i++;
            Thread.sleep(5);
            publishService.send(exChange, "CONFIRM_TEST", message);
        }
        Thread.sleep(1000 * 60 * 60 * 60);
    }

    @Test
    public void test1() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message:" + message);
        //exchange,queue 都正确,confirm被回调, ack=true
//        publishService.send(exChange, "CONFIRM_TEST", message);
        Thread.sleep(1000 * 60 * 60 * 60);
    }

    @Test
    public void test11() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message:" + message);
        //exchange,queue 都正确,confirm被回调, ack=true
//        publishService.send(exChange, "CONFIRM_TEST", message);
        Thread.sleep(1000 * 60 * 60 * 60);
    }

    @Test
    public void test111() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message:" + message);
        //exchange,queue 都正确,confirm被回调, ack=true
//        publishService.send(exChange, "CONFIRM_TEST", message);
        Thread.sleep(1000 * 60 * 60 * 60);
    }

    @Test
    public void test2() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test2---message:" + message);
        //exchange 错误,queue 正确,confirm被回调, ack=false
//    	publishService.send(exChange+"NO","CONFIRM_TEST",message);  
        publishService.send(exChange + "NO", "CONFIRM_TEST", message);
        Thread.sleep(1000);
    }

    @Test
    public void test3() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test3---message:" + message);
        //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
        publishService.send(exChange, "a", message);
//        Thread.sleep(1000);
    }

    @Test
    public void test4() throws InterruptedException {
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test4---message:" + message);
        //exchange 错误,queue 错误,confirm被回调, ack=false
        publishService.send(exChange + "NO", "CONFIRM_TEST", message);
        Thread.sleep(1000);
    }
}  