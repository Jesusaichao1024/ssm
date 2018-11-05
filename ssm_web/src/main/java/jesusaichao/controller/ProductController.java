package jesusaichao.controller;

import jesusaichao.domain.Product;
import jesusaichao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    /**
     * 查询所有的产品信息
     */
    @RequestMapping("/findAllProduct")
    public String findAllProduct(Model model) {
        List<Product> list = productService.findAllProduct();
        //添加到 请求域中
        model.addAttribute("productList", list);

        return "product/productList";
    }

    /**
     * 更新数据
     * 1.先查询数据
     */
    @RequestMapping("/updateProductUI")
    public String updateProductUI(Integer id, Model model) {
        //获取查询方法
        Product product = productService.findById(id);
        //传递到界面
        model.addAttribute("product", product);
        //跳转到添加页面
        return "product/productUpdate";
    }

    /**
     * 2更新数据
     */
    @RequestMapping("updateProduct")
    public String updateProduct(Product product) {
        //实现类实现方法
        productService.updateProduct(product);
        String s = "redirect:/product/findAllProduct";
        return s;
    }

    /**
     * 添加数据 1 先到添加界面
     */
    @RequestMapping("/addProductUI")
    public String addProductUI() {
        //跳转到添加页面
        return "product/productAdd";
    }

    /**
     * 2.添加数据
     */
    @RequestMapping("saveProduct")
    public String saveProduct(Product product) {
        //获取service层中的 方法
        productService.saveProduct(product);
        return "redirect:/product/findAllProduct";
    }

    /**
     * 删除数据
     */
    @RequestMapping("deleteById")
    public String deleteById(Integer id) {
        //获取删除数据
        productService.deleteById(id);
        return "redirect:/product/findAllProduct";
    }
}
