package jesusaichao.dao;

import jesusaichao.domain.Users;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
public interface UserDao {
    /**
     * 查询所有的用户
     *
     * @return
     */
    @Select("select * from SYS_USER")
    List<Users> findAllUser();

    /**
     * 根据id查询用户详细信息
     *实现一个用户有多个角色和多种权限的方法 一对多
     * property 是属性名的值
     * column 代表用什么去查询用户的角色信息 Role外键的取值范围
     * javaType 是查询多值的返回值类型
     * @param id
     * @return
     */
    @Select("select * from SYS_USER where ID =#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles",column = "id",javaType = List.class,
            many = @Many(select = "jesusaichao.dao.RoleDao.findRoleByUserId",fetchType = FetchType.LAZY)
            )
    })
    Users findRoleUserById(Integer id);

    /**
     * 根据用户名查询用户信息用户验证账户的登录操作
     * @exception
     * @param username
     * @return
     */
    @Select("select * from SYS_USER where USERNAME =#{username}")
    @Results({
            @Result(property ="roles",column = "id" ,javaType = List.class,
            many = @Many(select = "jesusaichao.dao.RoleDao.findRoleByUserId")
            )
    })
    Users findUserByName(String username);

    /**
     * 保存用户信息
     * @exception
     * @param user
     * @return
     */
    @Insert("insert into SYS_USER VALUES (COM_SEQUENCE.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void saveUser(Users user);

    /**
     * 删除用户的角色信息
     * @param userId
     */
    @Delete("delete from sys_user_role where userId = #{userId}")
    void deleteUserRole(Integer userId);

    /**
     * 添加用户的角色信息
     * @param userId
     * @param roleId
     */
    @Insert("insert into sys_user_role values (#{userId},#{roleId})")
    void saveUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 更新密码
     * @param id
     */
    @Update("update sys_user set password = #{password} where id = #{id}")
    void updatePassword(Integer id);
}
