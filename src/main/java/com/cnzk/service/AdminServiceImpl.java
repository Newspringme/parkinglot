package com.cnzk.service;

import com.cnzk.mapper.AdminMapper;
import com.cnzk.mapper.BillMapper;
import com.cnzk.mapper.ComboMapper;
import com.cnzk.mapper.RatesMapper;
import com.cnzk.pojo.*;
import com.cnzk.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @author su
 * @date 2020/6/5-15:31
 */
@Service
public class AdminServiceImpl implements AdminService
{
	@Resource
	private AdminMapper adminMapper;
	@Resource
	private RatesMapper ratesMapper;
	@Resource
	private ComboMapper comboMapper;
	@Resource
	private BillMapper billMapper;
    //登陆
	//登陆

	/**
	 * 将登陆账号密码拿去数据库验证
	 *
	 * @param map
	 * @param session
	 * @return
	 */
	@Override

	public String adminlogin(Map<String, Object> map, HttpSession session)
	{
		map.put("adminpass", MD5.machining(map.get("adminpass").toString()));//将管理员输入的密码转成MD5加密
		System.out.println(map);
		Admin tbAdmin2 = adminMapper.adminlogin(map);
		if (tbAdmin2 != null)
		{
			if (tbAdmin2.getRoleId() == 3)
			{
				return "您的权限不足，请返回收费端登陆";
			}
			if ("启用".equals(tbAdmin2.getAdminState()))
			{
				session.setAttribute("tbAdmin", tbAdmin2);//将管理员信息放到session
				return "success";
			}
			return "您已被禁止登陆！";
		}
		return "账号或密码错误";
	}


	//	查询管理员，包括带条件,分页,记录数
	@Override
	public LayuiData queryAdmin(Admin admin, int start, int pageSize)
	{
		List<Admin> list = adminMapper.queryAdmin(admin, start, pageSize);
		int count = adminMapper.queryAdminCount(admin);
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}

	//  添加管理员
	@Override
	public int addAdmin(Admin admin)
	{
		return adminMapper.addAdmin(admin);
	}

	//删除管理员
	@Override
	public int deleteAdmin(int[] array)
	{
		int num = adminMapper.deleteAdmin(array);
		return num;
	}

	//更新管理员信息
	@Override
	public int updateAdmin(Admin admin)
	{
		int num = adminMapper.updateAdmin(admin);
		return num;
	}


	//查计费规则列表
	@Override
	public LayuiData queryRatesList(HashMap<String, Object> condition) {
		List<TbRates> tbRoleList = ratesMapper.queryRatesList(condition);
		int count = ratesMapper.queryCount(condition);
		LayuiData layuiData=new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(tbRoleList);
		return layuiData;
	}

	//查月缴产品列表
	@Override
	public LayuiData queryComboList(HashMap<String, Object> condition) {
		List<TbCombo> tbRoleList = comboMapper.queryComboList(condition);
		int count = comboMapper.queryCount(condition);
		LayuiData layuiData=new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(tbRoleList);
		return layuiData;
	}
	//    修改规则金额
	@Override
	public Integer editRates(TbRates tbRates) {
		return ratesMapper.editRates(tbRates);
	}
	//    查金额规则
	@Override
	public TbRates queryPrice() {
		TbRates tbRates = ratesMapper.queryPrice();
		return tbRates;
	}
	//    新增月缴状态
	@Override
	public Integer addCombo(TbCombo tbCombo) {
		return comboMapper.addCombo(tbCombo);
	}
	//    修改月缴状态
	@Override
	public Integer updataCombo(TbCombo tbCombo) {
		return comboMapper.updataCombo(tbCombo);
	}
	//    修改月缴产品
	@Override
	public Integer editCombo(TbCombo tbCombo) {
		return comboMapper.editCombo(tbCombo);
	}
	//	查收支明细
	@Override
	public LayuiData queryBill(String page, String limit, String billNum, String billTime) {
		int startPage=Integer.parseInt(page);//获取页码;
		int pageSize=Integer.parseInt(limit);//每页数量
		int start = (startPage-1)*pageSize;//计算出起始查询位置
		HashMap<String, Object> map = new HashMap<>();
		map.put("start",start);
		map.put("pageSize",pageSize);
		if (null != billNum && !"".equals(billNum.trim()) && !"0".equals(billNum)) {
			map.put("billNum",billNum);
		}
		if (null != billTime && !"".equals(billTime.trim()) && !"0".equals(billTime)) {
			map.put("billTime",billTime);
		}
		List<TbBill> list=billMapper.queryBill(map);
		for (TbBill tbBill : list) {
			if (null==tbBill.getComboName()) {
				tbBill.setComboName("临时停车");
			}else{
				tbBill.setComboName("购买"+tbBill.getComboName());
			}
		}
		int count=billMapper.queryBillCount(map);
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}

}
