import jesusaichao.domain.Product;
import jesusaichao.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-service.xml")
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findAllProduct(){
        //获取方法
        List<Product> list = productService.findAllProduct();
        //遍历集合
        for (Product product : list) {
            System.out.println("product = " + product);
        }
    }
}
