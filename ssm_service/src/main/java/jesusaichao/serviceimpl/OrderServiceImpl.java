package jesusaichao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jesusaichao.dao.OrderDao;
import jesusaichao.domain.Orders;
import jesusaichao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS,readOnly = true)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    /**
     * 查询所有的订单信息
     */
    public List<Orders> findAllOrders() {
        return orderDao.findAllOrders();
    }

    /**
     * 根据id值查询订单信息
     * @param id
     * @return
     */
    @Override
    public Orders findOrderById(Integer id) {
        return orderDao.findOrderById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = false,propagation = Propagation.REQUIRED)
    public void updateOrders(Orders orders) {
        orderDao.updateOrder(orders);
    }

    /**
     * 分页显示订单列表
     * @exception
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @Override
    public PageInfo<Orders> findOrderPage(Integer pageNumber, Integer pageCount) {
        //获取分页插件的起始数据并在dao层方法之前调用执行
        PageHelper.startPage(pageNumber, pageCount);
        //查询数据库的所有的数据
        List<Orders> list = orderDao.findAllOrders();
        //获取插件对象并返回参数
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
