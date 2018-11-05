package jesusaichao.dao;

import jesusaichao.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 18:49
 * @Project_name: ssm01
 */
public interface RoleDao {
    /**
     * 查询所有的角色信息
     *
     * @return
     */
    @Select("select * from SYS_ROLE")
    List<Role> findAllRole();

    /**
     * 根据id值查询角色
     *并查询角色下面的所有的权限信息
     * @param id
     * @return
     */
    @Select("select * from SYS_ROLE where ID = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "permissions",javaType = List.class,
            many = @Many(select = "jesusaichao.dao.PermissionDao.findPermissionByRoleId")
            )
    })
    Role findPermissionByRoleId(Integer id);

    /**
     * 一对多的表中根据用户的id值查询角色信息 一个用户对应多个角色
     * @exception
     * @param id
     * @return
     */
    @Select("select sr.* from SYS_ROLE SR,SYS_USER_ROLE SUR where SR.ID=SUR.roleId and SUR.userId = #{id}")
    @Results({
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "jesusaichao.dao.PermissionDao.findPermissionByRoleId")
            )
    })
    List<Role> findRoleByUserId(Integer id);

    /**
     * 保存角色信息
     * @return
     * @param role
     */
    @Insert("insert into SYS_ROLE  values (COM_SEQUENCE.nextval,#{roleName},#{roleDesc})")
    void saveRole(Role role);

    /**
     * 删除角色下面的权限信息
     * @param roleId
     */
    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void deleteRolePermission(Integer roleId);

    /**
     * 保存角色下面的所有的权限信息
     * @param roleId
     * @param id
     */
    @Insert("insert into sys_role_permission values (#{pId},#{roleId})")
    void saveRolePermission(@Param("roleId") Integer roleId, @Param("pId") Integer id);
}
