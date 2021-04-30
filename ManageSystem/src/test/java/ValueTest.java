import com.btw.ManageSystem.ManageSystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-29 16:30
 */
@SpringBootTest(classes = ManageSystemApplication.class)
public class ValueTest {
    @Value("${manage.service-base-url}")
    String baseUrl;
    @Test
    void test(){
        System.out.println("baseurl:"+baseUrl);
    }
}