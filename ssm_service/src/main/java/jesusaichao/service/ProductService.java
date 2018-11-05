package jesusaichao.service;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:08
 */
public interface ProductService {
    /**
     * 查询所有的产品
     * @return
     */
    List<Product> findAllProduct();

    /**
     * 根据id值查询产品
     * @param id
     * @return Product
     */
    Product findById(Integer id);

    /**
     * 更新数据
     * @param product
     */
    void updateProduct(Product product);

    /**
     * 保存数据
     * @param product
     */
    void saveProduct(Product product);

    /**
     * 删除数据
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 得到分页数据
     * @param pageNumber
     * @param pageCount
     * @return
     */
    PageInfo<Product> findAllProductPage(Integer pageNumber, Integer pageCount);
}
