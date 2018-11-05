package jesusaichao.dao;

import jesusaichao.domain.Orders;
import jesusaichao.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
public interface OrderDao {
    /**
     * 查询所有的订单
     * @return
     * @Description 查询订单一对一
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product", javaType = Product.class, column = "productId",
                    one = @One(select = "jesusaichao.dao.ProductDao.findById"))
    })
    List<Orders> findAllOrders();

    /**
     * 根据id值查询数据
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "product", javaType = Product.class, column = "productId",
            one = @One(select = "jesusaichao.dao.ProductDao.findById"))
    })
    Orders findOrderById(Integer id);

    /**
     * 更新订单数据
     * @param orders
     */
    @Update("update orders set orderTime=#{orderTime},peopleCount=#{peopleCount},orderDesc=#{orderDesc} where id = #{id}")
    void updateOrder(Orders orders);
}
