package fruit.dao;

import fruit.pojo.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPass(String adminName,String password);
}
