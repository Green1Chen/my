package com.etc.flowershop.dao;

import java.sql.SQLException;

import com.etc.flowershop.entity.Flower;

public class FlowerDao extends BaseDao{

	public Flower getFlower(int id) throws SQLException {
		getConn();
		String sql="SELECT * FROM flower where f_id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		rs=ps.executeQuery();
		Flower f=null;
		if(rs.next()){
			f=new Flower();
			f.setF_name(rs.getString("f_name"));
			f.setCount(rs.getInt("count"));
			f.setPrice(rs.getDouble("price"));
			f.setF_id(rs.getInt("f_id"));
			f.setPic(rs.getString("pic"));
		}
	
		return f;
	}

}
