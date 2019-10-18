package com.etc.flowershop.service;

import java.sql.SQLException;

import com.etc.flowershop.dao.FlowerDao;
import com.etc.flowershop.entity.Flower;

public class FlowerService {

	public Flower getFlower(int id) throws SQLException {
		FlowerDao dao=new FlowerDao();
		Flower f=null;
		try {
			f=dao.getFlower(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dao.closeResource();
		}
		return f;
	}

}
