package com.renhouse.junit.dao;

import com.renhouse.dao.HouseDao;
import com.renhouse.dao.TenantDao;
import com.renhouse.dao.impl.TenantDaoImpl;
import com.renhouse.pojo.House;
import com.renhouse.pojo.Tenant;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TenantDaoImplTest {

    @Test
    public void queryTenantByTenantName() {
        TenantDao tenantDao = new TenantDaoImpl();
        System.out.println(tenantDao.queryTenantByTenantName("憨憨租客"));
    }

    @Test
    public void queryTenantByRealName() {
        TenantDao tenantDao = new TenantDaoImpl();
        System.out.println(tenantDao.queryTenantByRealName("爹"));
    }

    @Test
    public void addTenant() {
        TenantDao tenantDao = new TenantDaoImpl();
        String tenantName = "nihao";
        String tenantPassword = "qwert";
        String landlord = "woshibaba";
        String realName = "nihao";
        String phoneNumber = "14444444444";
        String email = "nihao@hgsss.com";
        Tenant tenant = new Tenant(null,tenantName,tenantPassword,landlord,realName,phoneNumber,email);
        tenantDao.addTenant(tenant);
    }

    @Test
    public void deleteTenantById() {
        TenantDao tenantDao = new TenantDaoImpl();
        System.out.println(tenantDao.deleteTenantById(5));
    }

    @Test
    public void updateTenant() {
        TenantDao tenantDao = new TenantDaoImpl();
        String tenantName = "nihao";
        String tenantPassword = "qwert";
        String landlord = "woshibaba";
        String realName = "nihao";
        String phoneNumber = "18888888888";
        String email = "nihao@hgsss.com";
        Tenant tenant = new Tenant(3,tenantName,tenantPassword,landlord,realName,phoneNumber,email);
        tenantDao.updateTenant(tenant);
    }

    @Test
    public void queryTenantById() {
        TenantDao tenantDao = new TenantDaoImpl();
        System.out.println(tenantDao.queryTenantById(3));
    }

    @Test
    public void queryTenants() {
        TenantDao tenantDao = new TenantDaoImpl();
        List<Tenant> tenants = tenantDao.queryTenants();
        for (Tenant tenant:tenants) {
            System.out.println(tenant);
        }
    }
}