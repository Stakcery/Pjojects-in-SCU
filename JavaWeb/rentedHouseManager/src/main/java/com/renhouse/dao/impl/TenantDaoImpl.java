package com.renhouse.dao.impl;

import com.renhouse.dao.BaseDao;
import com.renhouse.dao.TenantDao;
import com.renhouse.pojo.Tenant;

import java.util.List;

public class TenantDaoImpl extends BaseDao implements TenantDao {

    @Override
    public Tenant queryTenantByTenantName(String tenantName) {
        String sql = "select id,tenantName,tenantPassword,landlord,realName,phoneNumber,email from t_tenant where tenantName = ?";
        return queryForOne(Tenant.class,sql,tenantName);
    }

    @Override
    public List<Tenant> queryTenantByRealName(String realName) {
        String sql = "select id,tenantName,tenantPassword,landlord,realName,phoneNumber,email from t_tenant where realName = ?";
        return queryForList(Tenant.class,sql,realName);
    }

    @Override
    public int addTenant(Tenant tenant) {
        String sql = "insert into t_tenant(tenantName,tenantPassword,landlord,realName,phoneNumber,email)values(?,?,?,?,?,?)";
        return update(sql,tenant.getTenantName(),tenant.getTenantPassword(),tenant.getLandlord(),tenant.getRealName(),tenant.getPhoneNumber(),tenant.getEmail());
    }

    @Override
    public int deleteTenantById(Integer id) {
        String sql = "delete from t_tenant where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateTenant(Tenant tenant) {
        String sql = "update t_tenant set tenantName=?,tenantPassword=?,landlord=?,realName=?,phoneNumber=?,email=? where id = ?";
        return update(sql,tenant.getTenantName(),tenant.getTenantPassword(),tenant.getLandlord(),tenant.getRealName(),tenant.getPhoneNumber(),tenant.getEmail(),tenant.getId());
    }

    @Override
    public Tenant queryTenantById(Integer id) {
        String sql = "select * from t_tenant where id = ?";
        return queryForOne(Tenant.class,sql,id);
    }

    @Override
    public List<Tenant> queryTenants() {
        String sql = "select * from t_tenant";
        return queryForList(Tenant.class,sql);
    }

    /*
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_tenant";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Tenant> queryForPageItems(int begin, int pageSize) {
        String sql = "select select `id`,`tenantName`,`tenantPassword`,`landlord` from t_tenant limit ?,?";
        return queryForList(Tenant.class,sql,begin,pageSize);
    }*/
}