package com.renhouse.dao;

import java.util.List;
import com.renhouse.pojo.Tenant;

public interface TenantDao {

    /**
     * 根据租客名字查询租客
     * @param tenantName
     * @return 查询到的租客
     */
    public Tenant queryTenantByTenantName(String tenantName);

    /**
     * 根据真实姓名查询所有租客
     * @param realName
     * @return 查询到的所有租客
     */
    public List<Tenant> queryTenantByRealName(String realName);

    /**
     * 添加租客
     * @param tenant
     * @return 返回-1添加失败，其他值是影响的数据库行数
     */
    public int addTenant(Tenant tenant);

    /**
     * 删除租客
     * @param id
     * @return 返回-1删除失败，其他值是影响的数据库行数
     */
    public int deleteTenantById(Integer id);

    /**
     * 更新租客
     * @param tenant
     * @return 返回-1更新失败，其他值是影响的数据库行数
     */
    public int updateTenant(Tenant tenant);

    /**
     * 根据id查询租客
     * @param id
     * @return 查到的租客
     */
    public Tenant queryTenantById(Integer id);

    /**
     * 查询所有租客
     * @return
     */
    public List<Tenant> queryTenants();

//    Integer queryForPageTotalCount();

//    List<Tenant> queryForPageItems(int begin, int pageSize);

}
