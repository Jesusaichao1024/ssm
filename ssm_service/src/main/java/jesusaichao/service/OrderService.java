package jesusaichao.service;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Orders;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
public interface OrderService {
    /**
     * 查询所有的订单
     * @return
     */
    List<Orders> findAllOrders();

    /**
     * 根据id值查询数据
     * @return Orders
     * @param id
     */
    Orders findOrderById(Integer id);

    /**
     * 更新数据
     * @param orders
     */
    void updateOrders(Orders orders);

    /**
     * 分页显示订单列表
     * @param pageNumber
     * @param pageCount
     * @return
     */
    PageInfo<Orders> findOrderPage(Integer pageNumber, Integer pageCount);
}
