package jesusaichao.service;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
public interface UserService extends UserDetailsService {
    /**
     * 查询所有的用户
     * @return
     */
    List<Users> findAllUser();

    /**
     * 根据id查询用户详细信息
     * @param id
     * @return
     */
    Users findUserById(Integer id);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    void saveUser(Users user);

    /**
     * 维护用户和角色的中间表进行用户角色的增加和删除修改
     * @param userId
     * @param ids
     */
    void managerUserRole(Integer userId, Integer[] ids);

    /**
     * 更新密码
     * @param id
     */
    void updatePassWord(Integer id);

    /**
     * 分页数据的展示
     * @param pageNumber
     * @param pageCount
     * @return
     */
    PageInfo<Users> userPage(Integer pageNumber, Integer pageCount);
}
