package jesusaichao.controller;

import com.github.pagehelper.PageInfo;
import jesusaichao.dao.ProductDao;
import jesusaichao.domain.Product;
import jesusaichao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Controller
@RequestMapping("/productPage")
public class ProductPageController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAllProductPage")
    /**
     * @RequestParam (default = " 1 ")定义起始页数
     * @RequestParam (default = " 3 ")定义每页要显示的数量
     */
    public String findAllProductPage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "2") Integer pageCount) {
        //获取分页的封装对象
        PageInfo<Product> pageInfo = productService.findAllProductPage(pageNumber, pageCount);
        //携带数据到页面
        model.addAttribute("pageInfo", pageInfo);
        //跳转到指定页面
        return "product/productPageList";
    }
}
