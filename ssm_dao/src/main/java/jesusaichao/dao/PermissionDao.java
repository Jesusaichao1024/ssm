package jesusaichao.dao;

import jesusaichao.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 19:31
 * @Project_name: ssm01
 */
public interface PermissionDao {
    /**
     * 查询所有的权限
     * @return
     */
    @Select("select * from SYS_PERMISSION")
    List<Permission> findAllPermission();

    /**
     * 用户一对多的查询角色对应多个权限
     * @param id
     * @return
     */
    @Select("select sp.* from sys_permission sp ,sys_role_permission srp where sp.id=srp.permissionId and srp.roleId = #{id}")
    List<Permission> findPermissionByRoleId(Integer id);

    /**
     * 保存权限
     * @param permission
     */
    @Insert("insert into sys_permission values (COM_SEQUENCE.nextval,#{permissionName},#{url})")
    void savePermission(Permission permission);
}
