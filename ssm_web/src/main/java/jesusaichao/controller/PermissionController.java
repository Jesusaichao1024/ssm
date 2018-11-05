package jesusaichao.controller;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Permission;
import jesusaichao.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 19:37
 * @Project_name: ssm01
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    /**
     * 获取service中的属性值
     */
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有的权限信息
     * @exception
     * @param model
     * @return
     */
    @RequestMapping("/findAllPermission")
    public String findAllPermission(Model model) {
        //获取方法
        List<Permission> list = permissionService.findAllPermission();
        //存储到session域中
        model.addAttribute("permissionList", list);
        return "permission/permissionList";
    }

    /**
     * 添加权限 1.跳转到添加页面
     * @return
     */
    @RequestMapping("/addPermissionUI")
    public String addPermissionUI(){
        //页面跳转
        return "permission/permissionAdd";
    }

    /**
     * 权限的添加
     * @param permission
     * @return
     */
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission) {
        //获取service中的方法
        permissionService.savePermission(permission);
        //重定向到列表页面
        String path = "redirect:/permission/findAllPermission";
        return path;
    }

    /**
     * 查询权限列表
     * @return
     */
    @RequestMapping("/permissionDetail")
    public String permissionDetail(){

        return "permission/permissionDetail";
    }
    /**
     * 分页显示权限列表
     * @param model
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @RequestMapping("/permissionPage")
    public String permissionPage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageCount) {
        //获取分页数据
        PageInfo<Permission> pageInfo = permissionService.permissionPage(pageNumber, pageCount);
        //存储到session中
        model.addAttribute("pageInfo", pageInfo);
        return "permission/permissionList";
    }
}
