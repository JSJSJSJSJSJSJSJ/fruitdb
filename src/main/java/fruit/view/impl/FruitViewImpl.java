package fruit.view.impl;

import fruit.dao.FruitDao;
import fruit.dao.impl.FruitDaoImpl;
import fruit.pojo.Fruit;
import fruit.view.FruitView;

import java.util.List;
import java.util.Scanner;

public class FruitViewImpl implements FruitView {

    Scanner input = new Scanner(System.in);
    FruitDao fruitDao = new FruitDaoImpl();

    @Override
    public List<Fruit> showFruitList() {
        List<Fruit> fruitList = fruitDao.getFruitList();
        System.out.println("------------------------------------------------------");
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
        if(fruitList==null || fruitList.size()<=0){
            System.out.println("对不起，库存为空！");
        }else{
            for (int i = 0; i < fruitList.size(); i++) {
                Fruit fruit = fruitList.get(i);
                System.out.println(fruit);
            }
        }
        System.out.println("------------------------------------------------------");
        return fruitList;
    }

    @Override
    public void addFruit(){
        System.out.print("请输入水果名称：");
        String fname = input.next() ;
        Fruit fruit = fruitDao.getFruitByFname(fname);
        if(fruit==null){
            System.out.print("请输入水果单价：");
            int price = input.nextInt() ;
            System.out.print("请输入水果库存量：");
            int fcount = input.nextInt() ;
            System.out.print("请输入水果备注：");
            String remark = input.next() ;
            fruit = new Fruit(0,fname , price , fcount , remark ) ;
            fruitDao.addFruit(fruit);
        }else{ // 说明库存中有这个名称的水果 - 修改
            System.out.print("请输入追加的库存量：");
            int fcount = input.nextInt() ;
            fruit.setFcount(fruit.getFcount()+fcount);
            //调用DAO的修改方法
            fruitDao.updateFruit(fruit);
        }
        System.out.println("添加成功！");
    }
    @Override
    public void showFruitInfo(){
        System.out.print("请输入水果名称：");
        String fname = input.next() ;
        Fruit fruit = fruitDao.getFruitByFname(fname);
        if(fruit==null){
            System.out.println("对不起，没有找到指定的水果库存记录！");
        }else{
            System.out.println("------------------------------------------------------");
            System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
            System.out.println(fruit);
            System.out.println("------------------------------------------------------");
        }
    }
    @Override
    public void delFruit(){
        System.out.print("请输入水果名称：");
        String fname = input.next() ;
        Fruit fruit = fruitDao.getFruitByFname(fname);
        if(fruit==null){
            System.out.println("对不起，没有找到需要下架的水果信息！");
        }else{
            System.out.print("是否确认下架？(Y/N)");
            String slt = input.next() ;
            if("y".equalsIgnoreCase(slt)){
                fruitDao.delFruit(fname);
                System.out.println("下架成功！");
            }
        }
    }
}
