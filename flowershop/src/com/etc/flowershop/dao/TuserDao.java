package com.etc.flowershop.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.flowershop.entity.FDetail;
import com.etc.flowershop.entity.Flower;
import com.etc.flowershop.entity.Forder;
import com.etc.flowershop.entity.Tuser;

public class TuserDao extends BaseDao{
//登录
	public Tuser login(String name, String pwd) throws SQLException {
		getConn();
		String sql="SELECT * FROM tuser where u_name=? AND pwd=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, pwd);
		rs=ps.executeQuery();
		Tuser tu=null;
		if(rs.next()){
			tu=new Tuser();
			tu.setU_name(rs.getString("u_name"));
			tu.setRole(rs.getString("role"));
			tu.setU_id(rs.getInt("u_id"));
			//tu.setU_name(rs.getString("u_name"));
			//tu.setPwd(rs.getString("pwd"));
		}
		return tu;
	}
//显示所有鲜花信息
	public List<Flower> getAll() throws SQLException {
		getConn();
		String sql="select * from flower";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		List<Flower> list=new ArrayList();
		Flower f=null;
		while(rs.next()){
			f=new Flower();
			f.setF_name(rs.getString("f_name"));
			f.setPrice(rs.getDouble("price"));
			f.setPic(rs.getString("pic"));
			f.setF_id(rs.getInt("f_id"));
			list.add(f);
		}
		return list;
	}
//获取购物车中的信息
	public List<Flower> get(Tuser tu) throws SQLException {
		getConn();
		String sql="select f.price, f.f_name,f.pic,f.f_id "
				+ "from flower f,tuser t,car c "
				+ "WHERE f.f_id=c.f_id "
				+ "AND t.u_id=c.u_id "
				+ "AND t.u_id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, tu.getU_id());
		rs=ps.executeQuery();
		List<Flower> list=new ArrayList<Flower>();
		Flower f=null;
		while(rs.next()){
			f=new Flower();
			f.setF_name(rs.getString("f_name"));
			f.setPrice(rs.getDouble("price"));
			f.setPic(rs.getString("f.pic"));
			f.setF_id(rs.getInt("f_id"));
			list.add(f);
		}
		return list;  
	}
//向购物车中添加商品信息
	public void addFlower(int fid, Tuser tu) throws SQLException {
		getConn();
		String sql="INSERT INTO car(u_id,f_id) VALUES (?,?);";
		ps=con.prepareStatement(sql);
		ps. setInt(1, tu.getU_id());
		ps.setInt(2, fid);
		ps.executeUpdate();
		
		
	}
//查询购物车是否有当前物品
	public boolean getCarone(int fid, Tuser tu) throws SQLException {
		getConn();
		String sql="SELECT * FROM car WHERE u_id=? AND f_id=?";
	    ps=con.prepareStatement(sql);
	    ps. setInt(1, tu.getU_id());
		ps.setInt(2, fid);
	    rs=ps.executeQuery();
	    boolean flag=false;
	    if(rs.next()){
	    	flag=true;
	    }
	    return flag;
	  
		
	}
	//删除购物车信息
	public void delete(Tuser tu, int flowerid) throws SQLException {
		getConn();
		String sql="DELETE FROM car WHERE car.c_id=( SELECT * FROM ( SELECT car.c_id FROM car WHERE car.u_id=? AND car.f_id=? ) tmp)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, tu.getU_id());
		ps.setInt(2, flowerid);
		ps.executeUpdate();
	}
	//修改当前的用户余额
	public void updateCount(Tuser tu,double sum) throws SQLException {
		String sql="UPDATE tuser t set t.account=t.account-? WHERE t.u_id=?";
		ps=con.prepareStatement(sql);
		ps.setDouble(1, sum);
		ps.setInt(2, tu.getU_id());
		ps.executeUpdate();
	}
	//修改鲜花的数量
	public void updateFCount(int id, int count) throws SQLException {
		String sql="UPDATE flower f SET f.count=f.count-? WHERE f.f_id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, count);
		ps.setInt(2, id);
		ps.executeUpdate();
		
		
	}
	//生成订单信息
	public void addOrder(Forder o,Tuser tu) throws SQLException {
		String sql="INSERT INTO forder (u_id,o_date,price) VALUES(?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, tu.getU_id());
		ps.setDate(2, new Date(o.getO_date().getTime()));
		ps.setDouble(3, o.getPrice());
		ps.executeUpdate();
		o.setO_id(getOID());
		List<FDetail> list=o.getDetails();
		for (FDetail f : list) {
			addDetail(o, f);
		}
		
	}
	//获取o_id的值
	public int getOID() throws SQLException{
		String sql="select MAX(o_id) from forder";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		int no=0;
		if(rs.next()){
			no=rs.getInt(1);
		}
		return no;
		
	}
	//添加订单详情
	public void addDetail(Forder o,FDetail f) throws SQLException{
		System.out.println(o.getO_id());
		String sql="INSERT INTO detail (o_id,f_id,account,price) VALUES (?,?,?,?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, o.getO_id());
		ps.setInt(2, f.getF_id());
		ps.setInt(3, f.getAccount());
		ps.setDouble(4, f.getPrice());
		ps.executeUpdate();
	}
	public void deleCar(Tuser tu, String s) throws SQLException {
		String sql="DELETE from car where  u_id=? AND f_id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, tu.getU_id());
		if(s!=null||!s.equals("")){
			int id=Integer.parseInt(s);
		    ps.setInt(2, id);	
		}
		ps.executeUpdate();
		
	}
	//上传鲜花信息
	public void uploadF(Flower flower) throws SQLException {
		getConn();
		String sql="INSERT INTO flower (t_id,price,f_name,count,pic) VALUES (?,?,?,?,?);";
		ps=con.prepareStatement(sql);
		ps.setInt(1, flower.getT_id());
		ps.setDouble(2, flower.getPrice());
		ps.setString(3, flower.getF_name());
		ps.setInt(4, flower.getCount());
		ps.setString(5, flower.getPic());
		ps.executeUpdate();
	}
	//获取当前默认地址
	public String getAddress(Tuser tu) throws SQLException {
		getConn();
		String sql="SELECT a.adress FROM address a WHERE a.u_id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, tu.getU_id());
		rs=ps.executeQuery();
		String uaddress=null;
		if(rs.next()){
			uaddress=rs.getString("adress");
		}
		return uaddress;
		
	}
	//多条件查询
	public List<Forder> quaryOrder(String name, java.util.Date s, java.util.Date en) throws SQLException {
		getConn();
		String sql="SELECT u_id,o_date,price FROM forder WHERE 1=1 ";
		if(name!=null||!name.equals("")){
			//作业
		}
		if(s!=null&&!s.equals("")){
			sql+=" AND o_date>='"+new Date(s.getTime())+"'";
		}
		if(en!=null&&!en.equals("")){
			sql+=" AND o_date<='"+new Date(en.getTime())+"'";
		}
		st=con.createStatement();
		rs=st.executeQuery(sql);
		List<Forder> list=new ArrayList<Forder>();
		while(rs.next()){
			Forder f=new Forder();
			f.setU_id(rs.getInt("u_id"));
			f.setO_date(rs.getDate("o_date"));
			f.setPrice(rs.getDouble("price"));
			list.add(f);
		}
		return list;
	}
	public int getpage(int i) throws SQLException {
		getConn();
		
		return 0;
	}


}
