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
			// ��ӵ�ǰ��Ʒ�����ﳵ
			if (dao.getCarone(fid, tu)) {

			} else {
				dao.addFlower(fid, tu);
			}
			// �ӹ��ﳵ�л�ȡ��Ϣ,��ʾ��ҳ����
			f = dao.get(tu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}
		return f;
	}

	// ɾ�����ﳵ����Ϣ
	public void deleteCar(Tuser tu, int flowerid) {
		TuserDao dao = new TuserDao();
		try {
			dao.delete(tu, flowerid);
		} catch (Exception e) {
			Log.logger.debug(e.getMessage());
			e.printStackTrace();
		}

	}

	// ��ȡ���ﳵ����Ϣ
	public List<Flower> getCar(Tuser tu) throws SQLException {
		TuserDao dao = new TuserDao();
		List<Flower> f = new ArrayList<Flower>();
		try {
			// �ӹ��ﳵ�л�ȡ��Ϣ,��ʾ��ҳ����
			f = dao.get(tu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.closeResource();
		}
		return f;
	}

	// ʵ�ֹ���ҵ��
	public void buy(Tuser tu, String strs, Double sum, Map<String, Integer> flowercount,
			Map<String, Double> flowersum) throws SQLException {
		TuserDao dao = new TuserDao();
		try {
			//��������
			dao.openTran();
			// �޸ĵ�ǰ�û������
			dao.updateCount(tu,sum);
			//�޸����й�����ʻ�������
			String[] str=strs.split(",");
			for (String s : str) {
				int id=Integer.parseInt(s);
				int count=flowercount.get(s);
				dao.updateFCount(id,count);
			}
			//���ɶ���
			Forder o=new Forder(new Date(),sum);
			
			//���ɶ�������
			List<FDetail> details=new ArrayList<FDetail>();
			for (String string : str) {
				System.out.println(o.getO_id());
				FDetail f=new FDetail(flowercount.get(string),flowersum.get(string),o,Integer.parseInt(string));
				details.add(f);
			}
			o.setDetails(details);
			dao.addOrder(o,tu);
			//ɾ�����ﳵ���Ѿ��ύ���ʻ���Ϣ
			for (String s : str) {
				dao.deleCar(tu,s);
			}
			//�ύ����
			dao.commitTran();
		} catch (Exception e) {
			//�ع�����
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
    //��ȡ��ǰ�û���Ĭ�ϵ�ַ
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
  //��������ѯ
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
    //��ҳ��ʾ��ѯһ���ж���ҳ
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
