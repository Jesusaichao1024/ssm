package jesusaichao.dao;

import jesusaichao.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 9:41
 */
public interface ProductDao {
    /**
     * 查询所有的产品
     *
     * @return
     */
    @Select("select * from product")
    List<Product> findAllProduct();

    /**
     * 根据id值查询产品
     *
     * @param id
     * @return Product
     */
    @Select("select * from product where id = #{id}")
    Product findById(Integer id);

    /**
     * 更新数据
     * @param product
     */
    @Update("update product set productName=#{productName},cityName=#{cityName}," +
            " departureTime=#{departureTime},productPrice=#{productPrice}," +
            " productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void updateProduct(Product product);

    /**
     * 保存数据
     * @param product
     */
    @Insert("insert into product values(common_sequence.nextval," +
            "#{productNum},#{productName},#{cityName},#{departureTime}," +
            "#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    /**
     * 删除数据
     * @param id
     */
    @Delete("delete from product where id = #{id}")
    void deleteById(Integer id);
}
