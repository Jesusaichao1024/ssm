package jesusaichao.service;

import com.github.pagehelper.PageInfo;
import jesusaichao.domain.Permission;

import java.util.List;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.22
 * @Time: 19:32
 * @Project_name: ssm01
 */
public interface PermissionService {
    /**
     * 查询所有的权限信息
     * @return
     */
    List<Permission> findAllPermission();

    /**
     * 保存权限
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * 获分页数据
     * @param pageNumber
     * @param pageCount
     * @return
     */
    PageInfo<Permission> permissionPage(Integer pageNumber, Integer pageCount);
}
