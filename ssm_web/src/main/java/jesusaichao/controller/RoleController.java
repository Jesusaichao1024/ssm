package jesusaichao.controller;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Permission;
import jesusaichao.domain.Role;
import jesusaichao.service.PermissionService;
import jesusaichao.service.RoleService;
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
 * @Time: 18:48
 * @Project_name: ssm01
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    /**
     * 获取service层中方法
     */
    @Autowired
    private RoleService roleService;
    /**
     * 注入permission
     */
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有的角色信息列表展示
     * @param model
     * @return
     */
    @RequestMapping("/findAllRole")
    public String findAllRole(Model model) {
        //获取所有的角色信息
        List<Role> list = roleService.findAllRole();
        //存储到session域中
        model.addAttribute("roleList", list);
        return "role/roleList";
    }

    /**
     * 添加角色信息 1.跳转到添加页面
     * @return
     */
    @RequestMapping("/addRoleUI")
    public String addRoleUI() {
        return "role/roleAdd";
    }

    /**
     * 角色的添加
     * @param role
     * @return
     */
    @RequestMapping("addRole")
    public String addRole(Role role) {
        //获取service中的方法
        roleService.saveRole(role);
        String path = "redirect:/role/findAllRole";
        return path;
    }

    /**
     * 根据id中查询角色
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("/managerRolePermissionUI")
    public String managerRolePermissionUI(Integer roleId,Model model) {
        //获取service中的方法
        Role role = roleService.findPermissionByRoleId(roleId);
        //存储到session域中
        model.addAttribute("role", role);
        //获取所有的角色下面的权限信息
        List<Permission> permissions = role.getPermissions();
        //判断是否为空
        if (permissions != null && permissions.size() > 0) {
            //创建字符串
            StringBuilder stringBuilder = new StringBuilder();
            //遍历集合
            for (Permission permission : permissions) {
                //追加字符串
                stringBuilder.append(permission.getPermissionName()).append(",");
            }
            //存储到session域中
            model.addAttribute("permissionStr", stringBuilder.toString());
        }
        //获取所有的权限信息
        List<Permission> permissionList = permissionService.findAllPermission();
        //存储到session中
        model.addAttribute("permissionList", permissionList);
        //跳转到管理页面
        return "role/managerRolePermission";
    }

    /**
     * 添加权限信息
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/managerRolePermission")
    public String managerRolePermission(Integer roleId, Integer[] ids) {
        roleService.managerRolePermission(roleId, ids);
        String path = "redirect:/role/findAllRole";
        return path;
    }

    @RequestMapping("/rolePage")
    public String rolePage(Model model, @RequestParam(defaultValue = "1") Integer pageNumber, @RequestParam(defaultValue = "3") Integer pageCount) {
        //获取分页数据
        PageInfo<Role> pageInfo = roleService.rolePage(pageNumber, pageCount);
        //添加数据到jsp页面
        model.addAttribute("pageInfo", pageInfo);
        return "role/roleList";
    }
}
