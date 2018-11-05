package jesusaichao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jesusaichao.dao.RoleDao;
import jesusaichao.domain.Role;
import jesusaichao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 18:52
 * @Project_name: ssm01
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {
    /**
     * 获取dao层中的方法
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有的角色信息
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    /**
     * 保存角色信息
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    /**
     * 根据id值查询角色下面的所有的权限信息
     *
     * @param roleId
     * @return
     */
    @Override
    public Role findPermissionByRoleId(Integer roleId) {
        return roleDao.findPermissionByRoleId(roleId);
    }

    @Override
    public void managerRolePermission(Integer roleId, Integer[] ids) {
        //先删除权限信息
        roleDao.deleteRolePermission(roleId);
        //判断数据是否为空
        if (ids != null && ids.length > 0) {
            //遍历集合
            for (Integer id : ids) {
                //调用dao层保存数据
                roleDao.saveRolePermission(roleId, id);
            }
        }
    }

    /**
     * 分页数据的展示
     *
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @Override
    public PageInfo<Role> rolePage(Integer pageNumber, Integer pageCount) {
        //在获取dao层方法之前执行的方法
        PageHelper.startPage(pageNumber, pageCount);
        //查询所有的角色信息
        List<Role> allRole = roleDao.findAllRole();
        //添加到分页数据中
        PageInfo<Role> pageInfo = new PageInfo<>(allRole);
        //返回参数
        return pageInfo;
    }
}
