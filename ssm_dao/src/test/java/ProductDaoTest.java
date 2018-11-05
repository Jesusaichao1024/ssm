import jesusaichao.dao.OrderDao;
import jesusaichao.dao.ProductDao;
import jesusaichao.domain.Orders;
import jesusaichao.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 9:42
 */
public class ProductDaoTest {
    @Test
    public void findAllProduct() {
        //获取对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        //获取映射对象
        ProductDao productDao = applicationContext.getBean(ProductDao.class);
        //获取方法
        List<Product> list = productDao.findAllProduct();
        //遍历集合
        for (Product product : list) {
            System.out.println("product = " + product);
        }
    }

    @Test
    public void findAllOrders() {
        //获取对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        //获取代理对象
        OrderDao orderDao = applicationContext.getBean(OrderDao.class);
        //获取方法
        List<Orders> list = orderDao.findAllOrders();
        //遍历
        for (Orders orders : list) {
            System.out.println("orders = " + orders);
        }
    }
}
