package fruit.view.impl;

import fruit.dao.BusinessDao;
import fruit.dao.impl.BusinessDaoImpl;
import fruit.pojo.Business;
import fruit.view.BusinessView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessView{

    private Scanner input = new Scanner(System.in);

    @Override
    public void listBusinessAll() {
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null,null);
        System.out.println("交易编号\t交易名称\t交易介绍");
        for(Business b : list) {
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessExplain());
        }
    }

    @Override
    public void listBusiness() {
        String businessName = "";
        String businessAddress = "";

        String inputStr = "";
        System.out.println("是否需要输入交易名称关键词(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入交易名称关键词：");
            businessName = input.next();
        }


        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, businessAddress);
        System.out.println("交易编号\t交易名称\t交易介绍");
        for(Business b : list) {
            System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessExplain());
        }
    }

    @Override
    public void saveBusiness() {
        System.out.println("请输入交易名称：");
        String businessName = input.next();
        BusinessDao dao = new BusinessDaoImpl();
        int businessId = dao.saveBusiness(businessName);
        if(businessId>0) {
            System.out.println("新建交易成功！交易编号为："+businessId);
        }else {
            System.out.println("新建交易失败！");
        }
    }

    @Override
    public void removeBusiness() {
        System.out.println("请输入交易编号：");
        int businessId = input.nextInt();

        BusinessDao dao = new BusinessDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if(input.next().equals("y")) {
            int result = dao.removeBusiness(businessId);
            if(result==1) {
                System.out.println("删除交易成功！");
            }else {
                System.out.println("删除交易失败！");
            }
        }
    }

    @Override
    public void editBusiness() {
        System.out.println("请输入交易编号：");
        int businessId = input.nextInt();
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessId);
        System.out.println(business);

        String inputStr = "";
        System.out.println("是否修改交易名称(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的交易名称：");
            business.setBusinessName(input.next());
        }

        System.out.println("是否修改交易介绍(y/n)：");
        inputStr = input.next();
        if(inputStr.equals("y")) {
            System.out.println("请输入新的交易介绍：");
            business.setBusinessExplain(input.next());
        }

        int result = dao.updateBusiness(business);
        if(result>0) {
            System.out.println("\n修改交易信息成功！\n");
        }else {
            System.out.println("\n修改交易信息失败！\n");
        }
    }

}
