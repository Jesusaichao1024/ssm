package jesusaichao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jesusaichao.dao.UserDao;
import jesusaichao.domain.Role;
import jesusaichao.domain.Users;
import jesusaichao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description: Class
 * Date: 18.10.19
 * Time: 10:44
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<Users> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 根据id值查询用户的详细信息
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public Users findUserById(Integer id) {
        return userDao.findRoleUserById(id);
    }

    /**
     * @param user
     * @return
     * @Description 保存用户信息
     */
    @Override
    public void saveUser(Users user) {
        String encode = passwordEncoder.encode(user.getPassword().trim());
        user.setPassword(encode);
        userDao.saveUser(user);
    }

    /**
     * 进行用户的角色增删改
     * @param userId
     * @param ids
     */
    @Override
    public void managerUserRole(Integer userId, Integer[] ids) {
        //已有用户的角色先删除再添加用户的角色信息
        userDao.deleteUserRole(userId);
        //用户没有角色直接添加角色信息
        if (ids != null && ids.length > 0) {
            //遍历用户的所有角色信息
            for (Integer id : ids) {
                userDao.saveUserRole(userId, id);
            }
        }
    }

    /**
     * 更新密码
     * @param id
     */
    @Override
    public void updatePassWord(Integer id) {
        userDao.updatePassword(id);
    }

    /**
     * 分页数据展示
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @Override
    public PageInfo<Users> userPage(Integer pageNumber, Integer pageCount) {
        //获取分页数据在dao层方法之前执行
        PageHelper.startPage(pageNumber, pageCount);
        //查询所有的用户信息
        List<Users> list = userDao.findAllUser();
        //添加到分页数据中
        PageInfo<Users> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 覆写框架的loadUserBuUsername方法 用于验证框架的user对象
     * @exception
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取数据库中的用户信息
        Users users = userDao.findUserByName(username);
        //创建一个权限用户
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        //判断用户是否为空
        if (users != null) {
            //获取角色的结合
            List<Role> roles = users.getRoles();
            //遍历集合
            for (Role role : roles) {
                //设置用户的角色
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        //使用数据库初始化框架的user对象
        User user = new User(users.getUsername(), users.getPassword(), authorities);
        return user;
    }
}
