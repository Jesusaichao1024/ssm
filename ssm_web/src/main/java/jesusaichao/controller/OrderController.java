package jesusaichao.controller;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Orders;
import jesusaichao.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrders")
    public String findAllOrders(Model model) {
        //获取service中的方法
        List<Orders> list = orderService.findAllOrders();
        //放在请求域中
        model.addAttribute("order", list);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        //跳转界面
        return "order/orderList";
    }

    /**
     * 跳转到修改数据页面
     * @param id
     * @return
     */
    @RequestMapping("/updateOrderUI")
    public String updateOrderUI(Integer id,Model model) {
        //获取service中的方法
        Orders orders = orderService.findOrderById(id);
        //携带数据到jsp界面
        System.out.println(orders);
        model.addAttribute("orders", orders);
        return "order/orderUpdate";
    }

    @RequestMapping("/updateOrders")
    public String updateOrders(Orders orders) {
        orderService.updateOrders(orders);
        String s = "redirect:/orders/findAllOrders";
        return s;
    }

    @RequestMapping("/orderPage")
    public String orderPage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageCount) {
        //获取分页数据
        PageInfo<Orders> pageInfo = orderService.findOrderPage(pageNumber, pageCount);
        //存储到session域中
        model.addAttribute("pageInfo", pageInfo);
        return "order/orderPageList";
    }
}
