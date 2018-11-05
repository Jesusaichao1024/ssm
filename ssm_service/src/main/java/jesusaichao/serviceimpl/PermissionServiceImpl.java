package jesusaichao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jesusaichao.dao.PermissionDao;
import jesusaichao.domain.Permission;
import jesusaichao.service.PermissionService;
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
 * @Time: 19:33
 * @Project_name: ssm01
 */
@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = false)
public class PermissionServiceImpl implements PermissionService {
    /**
     * 获取dao层中的属性值
     */
    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Permission> findAllPermission() {
        //获取dao层中的方法
        List<Permission> list = permissionDao.findAllPermission();
        return list;
    }

    /**
     * 保存权限
     * @param permission
     */
    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    /**
     * 获取分页数据
     *
     * @param pageNumber
     * @param pageCount
     * @return
     */
    @Override
    public PageInfo<Permission> permissionPage(Integer pageNumber, Integer pageCount) {
        //必须到层方法之前调用的方法
        PageHelper.startPage(pageNumber, pageCount);
        //获取dao层数据
        List<Permission> allPermission = permissionDao.findAllPermission();
        //存储到分页插件中
        PageInfo<Permission> pageInfo = new PageInfo<>(allPermission);
        return pageInfo;
    }
}
