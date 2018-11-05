package jesusaichao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jesusaichao.dao.ProductDao;
import jesusaichao.domain.Product;
import jesusaichao.service.ProductService;
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
 * Time: 10:09
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    /**
     * 获取dao层中的属性
     */
    @Autowired
    private ProductDao productDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
    public List<Product> findAllProduct() {
        return productDao.findAllProduct();
    }

    /**
     * 根据id值查询产品来更新数据
     */
    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    /**
     * 更新数据
     */
    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    /**
     * 保存数据
     */
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    /**
     * 删除数据
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }

    /**
     * 获取分页的集合
     * @exception
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @Override
    public PageInfo<Product> findAllProductPage(Integer pageNumber, Integer pageCount) {
        //必须在dao层执行前开启静态方法
        PageHelper.startPage(pageNumber, pageCount);
        //获取dao层查询所有产品数据
        List<Product> list = productDao.findAllProduct();
        //获取插件的对象
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
