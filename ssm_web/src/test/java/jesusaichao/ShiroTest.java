package jesusaichao;

import jesusaichao.domain.Users;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
public class ShiroTest {
    @Test
    public void test(){
        Md5Hash hash = new Md5Hash("123", "hh", 2);
        System.out.println("hash = " + hash.toString());
    }
}
