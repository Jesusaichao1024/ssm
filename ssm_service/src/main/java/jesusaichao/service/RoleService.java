package jesusaichao.service;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 18:51
 * @Project_name: ssm01
 */
public interface RoleService {
    /**
     * 查询所有的角色信息
     * @return
     */
    List<Role> findAllRole();

    /**
     * 保存角色信息
     * @param role
     */
    void saveRole(Role role);

    /**
     * 根据id值查询角色信息下的所有权限
     * @param roleId
     * @return
     */
    Role findPermissionByRoleId(Integer roleId);

    /**
     * 保存权限信息
     * @param roleId
     * @param ids
     */
    void managerRolePermission(Integer roleId, Integer[] ids);

    /**
     * 分页数据的展示
     * @param pageNumber
     * @param pageCount
     * @return
     */
    PageInfo<Role> rolePage(Integer pageNumber, Integer pageCount);
}
