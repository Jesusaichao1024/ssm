package jesusaichao.controller;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Role;
import jesusaichao.domain.Users;
import jesusaichao.service.RoleService;
import jesusaichao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Controller
/**
 * @Author Jesusaichao
 * @RelesAllowed JSR250注解的支持
 * @Secured security直接方式的开启
 * @PreAuthorize pre-post注解的开启
 */
/**
 * @Author Jesusaichao
 * @RolesAllowed("ROLE_ADMIN")
 * @Secured("ROLE_ADMIN")
 * @PreAuthorize("hasAnyRole('ROLE_ADMIN')")*/
@Secured("ROLE_ADMIN")
@RequestMapping("/user")
public class UserController {
    /**
     * 依赖注入
     */
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有的用户列表显示
     *
     * @param model
     * @return
     */
    @RequestMapping("/findAllUser")
    public String findAllUser(Model model) {
        //查询所有的用户
        List<Users> list = userService.findAllUser();
        System.out.println("list = " + list);
        //存储到session域中
        model.addAttribute("userList", list);
        return "user/userList";
    }

    /**
     * 查看用户的详细信息 多对多的关系
     * @exception
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/managerUserRoleUI")
    public String managerUserRoleUI(Integer id, Model model) {
        //获取service中的方法
        Users user = userService.findUserById(id);
        //获取用户下面所对应的角色信息
        List<Role> list = user.getRoles();
        //判断是否为空
        if (list != null && list.size() > 0) {
        //创建字符拼接器
        StringBuilder stringBuilder = new StringBuilder();
            for (Role role : list) {
                //拼接角色的信息
                stringBuilder.append(role.getRoleName()).append(",");
            }
            //存放到session域中
            model.addAttribute("roleStr", stringBuilder.toString());
        }
        //获取角色的所有信息
        List<Role> roleList = roleService.findAllRole();
        //存储到session域中
        model.addAttribute("roles", roleList);
        //添加到session域中
        model.addAttribute("user", user);
        return "user/managerUserRole";
    }

    /**
     * 根据用户的id值进行用户的角色的增删改操作
     *
     * @param userId
     * @param ids
     * @return
     */
    @RequestMapping("/managerUserRole")
    public String managerUserRole(Integer userId, Integer[] ids) {
        //获取service中的方法
        userService.managerUserRole(userId, ids);
        //重定向到用户列表界面
        String path = "redirect:/user/findAllUser";
        return path;
    }

    /**
     * 查询用户的详细信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/userDetail")
    public String userDetail(Integer id, Model model) {
        //获取service中的方法
        Users user = userService.findUserById(id);
        //添加到session域中
        model.addAttribute("user", user);
        return "user/userDetail";
    }

    /**
     * 添加用户信息 1.跳转添加页面
     *
     * @return
     */
    @RequestMapping("/addUserUI")
    public String addUserUI() {
        return "user/userAdd";
    }

    /**
     * 2.添加数据到数据库跳转到展示列表页面
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(Users user) {
        //获取添加数据的方法
        userService.saveUser(user);

        String path = "redirect:/user/findAllUser";
        return path;
    }

    /**
     * 用户更新密码
     * @param id
     * @return
     */
    @RequestMapping("/updatePassWord")
    public String updatePassWord(Integer id) {
        userService.updatePassWord(id);
        String path = "redirect:/user/findAllUser";
        return path;
    }

    @RequestMapping("userPage")
    public String userPage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageCount) {
        //获取分页数据
        PageInfo<Users> pageInfo = userService.userPage(pageNumber, pageCount);
        //存储到session域中
        model.addAttribute("pageInfo", pageInfo);
        return "user/userList";
    }
}
