package fruit.dao;

import fruit.pojo.Business;

import java.util.List;

public interface BusinessDao {

    public List<Business> listBusiness(String businessName,String businessAddress);
    public int saveBusiness(String businessName);
    public int removeBusiness(int businessId);
    public Business getBusinessById(Integer businessId);
    public int updateBusiness(Business business);
}
