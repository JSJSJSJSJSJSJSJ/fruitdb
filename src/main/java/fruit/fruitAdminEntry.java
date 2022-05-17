package fruit;

import fruit.pojo.Admin;
import fruit.view.AdminView;
import fruit.view.BusinessView;
import fruit.view.FruitView;
import fruit.view.impl.AdminViewImpl;
import fruit.view.impl.BusinessViewImpl;
import fruit.view.impl.FruitViewImpl;

import java.util.Scanner;


public class fruitAdminEntry {
    public void work() {
        Scanner input = new Scanner(System.in);
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t   水果商店管理系统  \t\t\t|");
        System.out.println("---------------------------------------------------------");

        AdminView adminView = new AdminViewImpl();
        Admin admin = adminView.login();
        if (admin != null) {
            int menu = 0;
            while (menu != 3) {
                System.out.println("\n========= 1.水果信息管理=2.交易信息管理==3.退出系统 =========");
                System.out.println("请输入你的选择：");
                menu = input.nextInt();
                switch (menu) {
                    case 1:
                        f_manager();
                        break;
                    case 2:
                        b_manager();
                        break;
                    case 3:
                        System.out.println("---------------欢迎下次光临水果库存管理系统--------------");
                        break;
                    default:
                        System.out.println("没有这个选项！\n");
                        break;
                }
            }
        } else{
            System.out.println("\n管理员名称或密码输入错误!\n");
        }
    }

    private void f_manager() {
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t   水果信息管理  \t\t\t|");
        System.out.println("---------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        FruitView fruitView = new FruitViewImpl();
        int menu = 0;
        while (menu != 5) {
            //输出主菜单
            System.out.println("\n========= 1.库存水果=2.添加水果库存=3..根据名称查询特定库存=4.删除特定库存记录=5.退出系统 =========");
            System.out.println("请输入你的选择：");
            menu = input.nextInt();
            switch (menu) {
                case 1:
                    fruitView.showFruitList();
                    break;
                case 2:
                    fruitView.addFruit();
                    break;
                case 3:
                    fruitView.showFruitInfo();
                    break;
                case 4:
                    fruitView.delFruit();
                    break;
                case 5:
                    System.out.println("---------------欢迎下次光临水果库存管理系统--------------");
                    break;
                default:
                    System.out.println("没有这个选项！\n");
                    break;
            }
        }
    }
    private void b_manager() {
        System.out.println("---------------------------------------------------------");
        System.out.println("|\t\t\t   交易信息管理  \t\t\t|");
        System.out.println("---------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        BusinessView businessView = new BusinessViewImpl();
        int menu = 0;
        while (menu != 6) {
            //输出主菜单
            System.out.println("\n========= 1.所有交易列表=2.搜索交易=3.新建交易=4.删除交易=5.修改交易=6.退出系统 =========");
            System.out.println("请输入你的选择：");
            menu = input.nextInt();
            switch (menu) {
                case 1:
                    businessView.listBusinessAll();
                    break;
                case 2:
                    businessView.listBusiness();
                    break;
                case 3:
                    businessView.saveBusiness();
                    break;
                case 4:
                    businessView.removeBusiness();
                    break;
                case 5:
                    businessView.editBusiness();
                    break;
                case 6:
                    System.out.println("---------------欢迎下次光临水果库存管理系统--------------");
                    break;
                default:
                    System.out.println("没有这个选项！\n");
                    break;
            }
        }
    }
public static void main(String[] args) {new fruitAdminEntry().work();}
}
