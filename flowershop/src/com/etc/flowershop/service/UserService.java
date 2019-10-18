package com.etc.flowershop.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.etc.flowershop.dao.TuserDao;
import com.etc.flowershop.entity.FDetail;
import com.etc.flowershop.entity.Flower;
import com.etc.flowershop.entity.Forder;
import com.etc.flowershop.entity.Tuser;
import com.etc.javademo.util.Log;

public class UserService {

	public Tuser login(String name, String pwd) throws SQLException {
		Tuser tu = null;
		TuserDao dao = new TuserDao();
		try {
			tu = dao.login(name, pwd);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}
		return tu;
	}

	public List<Flower> getAll() throws SQLException {
		TuserDao dao = new TuserDao();
		List<Flower> list = null;
		try {
			list = dao.getAll();

		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}

		return list;
	}

	public List<Flower> addCar(int fid, Tuser tu) throws SQLException {
		TuserDao dao = new TuserDao();
		List<Flower> f = new ArrayList<Flower>();
		try {
			// 添加当前产品到购物车
			if (dao.getCarone(fid, tu)) {

			} else {
				dao.addFlower(fid, tu);
			}
			// 从购物车中获取信息,显示在页面上
			f = dao.get(tu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}
		return f;
	}

	// 删除购物车的信息
	public void deleteCar(Tuser tu, int flowerid) {
		TuserDao dao = new TuserDao();
		try {
			dao.delete(tu, flowerid);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}

	}

	// 获取购物车的信息
	public List<Flower> getCar(Tuser tu) throws SQLException {
		TuserDao dao = new TuserDao();
		List<Flower> f = new ArrayList<Flower>();
		try {
			// 从购物车中获取信息,显示在页面上
			f = dao.get(tu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}
		return f;
	}

	// 实现购买业务
	public void buy(Tuser tu, String strs, Double sum, Map<String, Integer> flowercount,
			Map<String, Double> flowersum) throws SQLException {
		TuserDao dao = new TuserDao();
		try {
			//启用事务
			dao.openTran();
			// 修改当前用户的余额
			dao.updateCount(tu,sum);
			//修改所有购物的鲜花的数量
			String[] str=strs.split(",");
			for (String s : str) {
				int id=Integer.parseInt(s);
				int count=flowercount.get(s);
				dao.updateFCount(id,count);
			}
			//生成订单
			Forder o=new Forder(new Date(),sum);
			
			//生成订单详情
			List<FDetail> details=new ArrayList<FDetail>();
			for (String string : str) {
				System.out.println(o.getO_id());
				FDetail f=new FDetail(flowercount.get(string),flowersum.get(string),o,Integer.parseInt(string));
				details.add(f);
			}
			o.setDetails(details);
			dao.addOrder(o,tu);
			//删除购物车中已经提交的鲜花信息
			for (String s : str) {
				dao.deleCar(tu,s);
			}
			//提交事务
			dao.commitTran();
		} catch (Exception e) {
			//回滚事务
			dao.rollbackTran();
          e.printStackTrace();
          Log.logger.debug(e.getMessage());
		} finally {
          dao.closeResource();
		}

	}

	public void uploadF(Flower flower) throws SQLException {
		TuserDao dao=new TuserDao();
		try {
			dao.uploadF(flower);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			dao.closeResource();
		}
		
	}
    //获取当前用户的默认地址
	public String getAddress(Tuser tu) throws SQLException {
		TuserDao dao=new TuserDao();
		String uaddress=null;
		try {
			uaddress=dao.getAddress(tu);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			dao.closeResource();
		}
		return uaddress;
	}
  //多条件查询
	public List<Forder> quaryOrder(String name, Date st, Date en) throws SQLException {
		TuserDao dao=new TuserDao();
		List<Forder> list=new ArrayList<Forder>();
		try {
			list=dao.quaryOrder(name,st,en);
		} catch (Exception e) {
		Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			dao.closeResource();
		}
		return list;
		
	}
    //分页显示查询一共有多少页
	public int getpage(int i) throws SQLException {
		TuserDao dao=new TuserDao();
		int page=0;
		try {
			page=dao.getpage(i);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}finally{
			dao.closeResource();
		}
		return page;
	}

}
