package fruit.dao.impl;

import fruit.dao.FruitDao;
import fruit.pojo.Fruit;
import fruit.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitDaoImpl implements FruitDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public FruitDaoImpl() {
    }

    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> list = new ArrayList<Fruit>();
        String sql = "select * from t_fruit";
        try {
            this.con = DBUtil.getConnection();
            this.pst = this.con.prepareStatement(sql);
            this.rs = this.pst.executeQuery();
            //从t_fruit取出数据存入fruit的对象，然后添加到列表
            while(this.rs.next()) {
                Fruit fruit = new Fruit();
                fruit.setFid(this.rs.getInt("fid"));
                fruit.setFname(this.rs.getString("fname"));
                fruit.setPrice(this.rs.getInt("price"));
                fruit.setFcount(this.rs.getInt("fcount"));
                fruit.setRemark(this.rs.getString("remark"));
                list.add(fruit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(this.rs, this.pst, this.con);
        }
        return list;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        boolean addFlag = false;
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        try {
            this.con = DBUtil.getConnection();
            this.pst = this.con.prepareStatement(sql);
            this.pst.setString(1, fruit.getFname());
            this.pst.setInt(2, fruit.getPrice());
            this.pst.setInt(3, fruit.getFcount());
            this.pst.setString(4, fruit.getRemark());
            if (this.pst.executeUpdate() > 0) addFlag = true;
            else addFlag = false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(this.rs, this.pst, this.con);
        }
        return addFlag;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        boolean updateFlag = false ;
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        try {
            this.con = DBUtil.getConnection();
            this.pst = this.con.prepareStatement(sql);
            this.pst.setString(1, fruit.getFname());
            this.pst.setInt(2, fruit.getPrice());
            this.pst.setInt(3, fruit.getFcount());
            this.pst.setString(4, fruit.getRemark());
            if (this.pst.executeUpdate() > 0) updateFlag = true;
            else updateFlag = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(this.rs, this.pst, this.con);
        }
        return updateFlag;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ? ";
        try {
            this.con= DBUtil.getConnection();
            this.pst = this.con.prepareStatement(sql);
            this.pst.setString(1,fname);
            rs = pst.executeQuery();
            if(rs.next()){
                int fid = rs.getInt(1);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);
                return new Fruit(fid , fname , price , fcount , remark ) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(this.rs, this.pst, this.con);
        }
        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        boolean delFlag = false;
        String sql = "delete from t_fruit where fname like ? " ;
        try {
            this.con = DBUtil.getConnection();
            this.pst = this.con.prepareStatement(sql);
            this.rs = this.pst.getGeneratedKeys();
            this.con.commit();
            if (this.pst.executeUpdate() > 0) delFlag = true;
            else delFlag = false;
        } catch (SQLException e) {
            try {
                if (this.con != null) {
                    this.con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(this.rs, this.pst, this.con);
        }
        return delFlag;
    }
}