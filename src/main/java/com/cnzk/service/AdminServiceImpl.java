package com.cnzk.service;

import com.cnzk.mapper.*;
import com.cnzk.pojo.*;
import com.cnzk.utils.MD5;
import com.cnzk.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	@Resource
	private ParamMapper paramMapper;
	@Resource
	private SlideshowMapper slideshowMapper;
	@Resource
	private FeedbackMapper feedbackMapper;
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
	//	上传头像
	@Override
	public int uploadAdminImg(String headImg, String adminName)
	{
		return adminMapper.uploadAdminImg(headImg,adminName);
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

	@Override
	public Integer updateState(Admin admin) {
		return adminMapper.updateState(admin);
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
			if(tbBill.getBillState()==1){
				tbBill.setBillType("支付成功");
			}else{
				tbBill.setBillType("支付失败");
			}
		}
		int count=billMapper.queryBillCount(map);
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}
//渠道量统计
	@Override
	public HashMap<String, Object> showBillStatistics(HashMap<String, Object> condition) throws ParseException {
		HashMap<String,Object> statisticsMap = new HashMap<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
		String date = df.format(new Date());//获取当前时间
		List dateSection = new ArrayList();
		if (condition.size()>0) {
			if ("1".equals(condition.get("mystatic"))) {
//                获取用户数量
				Integer appccount = billMapper.findWeekAllCount();
				System.out.println("本周的总金额" + appccount);
				List XList = new ArrayList<String>(Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六","周日"));
				List tempList = new ArrayList();
				List comboList = new ArrayList();
				for (int i = 1; i < 8; i++) {
					condition.put("dayid", i);
					Integer tempDayCount = billMapper.showWeekTempStatistics(condition);
					if (tempDayCount==null){
						tempDayCount=0;
					}
					System.out.println("星期" + i + "临时收入金额为" + tempDayCount);
					tempList.add(tempDayCount);
					Integer comboDayCount = billMapper.showWeekComboStatistics(condition);
					if (comboDayCount==null){
						comboDayCount=0;
					}
					System.out.println("星期" + i + "月缴收入金额为" + comboDayCount);
					comboList.add(comboDayCount);
				}
				statisticsMap.put("appccount", appccount);
				statisticsMap.put("XList", XList);
				statisticsMap.put("tempList", tempList);
				statisticsMap.put("comboList", comboList);
			} else if ("2".equals(condition.get("mystatic"))) {
				dateSection = TimeUtils.getDateSection(date);
				Integer appccount = billMapper.findMonthAllCount();
				System.out.println("本月的总金额" + appccount);
				List XList = TimeUtils.weeklist;
				List tempList = new ArrayList();
				List comboList = new ArrayList();
				for (int i = 0; i < dateSection.size(); i = i + 2) {
					condition.put("daytime1", dateSection.get(i));
					condition.put("daytime2", dateSection.get(i + 1));
					Integer tempWeekCount = billMapper.showMonthTempStatistics(condition);
					if (tempWeekCount==null){
						tempWeekCount=0;
					}
					System.out.println("临时周金额为" + tempWeekCount);
					tempList.add(tempWeekCount);
					Integer comboWeekCount = billMapper.showMonthComboStatistics(condition);
					if (comboWeekCount==null){
						comboWeekCount=0;
					}
					System.out.println("月缴周金额为" + comboWeekCount);
					comboList.add(comboWeekCount);
				}
				statisticsMap.put("appccount", appccount);
				statisticsMap.put("XList", XList);
				statisticsMap.put("tempList", tempList);
				statisticsMap.put("comboList", comboList);
			} else if ("3".equals(condition.get("mystatic"))) {
				List dateSection1 = TimeUtils.getYearSection();
				condition.put("monthtime1", dateSection1.get(0));
				condition.put("monthtime2", dateSection1.get(6));
				Integer appccount = billMapper.findYearAllCount(condition);
				System.out.println("近半年的总金额为" + appccount);
				List XList = TimeUtils.moulist;
				List tempList = new ArrayList();
				List comboList = new ArrayList();

				for (int i = 0; i < dateSection1.size(); i++) {
					if(i<6) {
						condition.put("daytime3", dateSection1.get(i));
						condition.put("daytime4", dateSection1.get(i + 1));

						Integer tempMouthCount = billMapper.showYearTempStatistics(condition);
						if (tempMouthCount==null){
							tempMouthCount=0;
						}

						Integer comboMonthCount = billMapper.showYearComboStatistics(condition);
						if (comboMonthCount==null){
							comboMonthCount=0;
						}

						if (i==5){
                            System.out.println("condition="+condition);
							condition.put("lacktime", dateSection1.get(i + 1));
                            System.out.println("condition="+condition);
                            Integer tempMouthCount1 = billMapper.lackTempMoney(condition);
                            Integer comboMonthCount1 = billMapper.lackComboMoney(condition);
                            if (tempMouthCount1!=null){
                                tempMouthCount += billMapper.lackTempMoney(condition);
                            }
                            if (comboMonthCount1!=null){
                                comboMonthCount += billMapper.lackComboMoney(condition);
                            }
						}
						System.out.println("临时月金额为" + tempMouthCount);
						System.out.println("月缴月金额为" + comboMonthCount);
						tempList.add(tempMouthCount);
						comboList.add(comboMonthCount);
					}
				}
				statisticsMap.put("appccount", appccount);
				statisticsMap.put("XList", XList);
				statisticsMap.put("tempList", tempList);
				statisticsMap.put("comboList", comboList);
			}
		}
		System.out.println(statisticsMap.toString());
		return statisticsMap;
	}



	@Override
	public LayuiData queryParam( int start, int pageSize) {
		List<TbParam> list = paramMapper.queryParam( start, pageSize);
		int count = paramMapper.queryParamCount();
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}

	@Override
	public Integer editParam(TbParam tbParam) {
		return paramMapper.editParam(tbParam);
	}

	@Override
	public LayuiData querySlideShow(int start, int pageSize) {
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		List<TbSlideshow> list = slideshowMapper.querySlideShow(start,pageSize);
		int count = slideshowMapper.querySlideShowCount();
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}

	@Override
	public Integer addSlideShow(TbSlideshow tbSlideshow) {
		return slideshowMapper.addSlideShow(tbSlideshow);
	}

	@Override
	public Integer deleteSlideShow(TbSlideshow tbSlideshow) {
		return slideshowMapper.deleteSlideShow(tbSlideshow);
	}

	@Override
	public Integer editSlideShow(TbSlideshow tbSlideshow) {
		return slideshowMapper.editSlideShow(tbSlideshow);
	}

	@Override
	public String queryComboValue(String comboId) {
		return comboMapper.queryComboValue(comboId);
	}

	@Override
	public LayuiData queryfeedback(int start, int pageSize) {
		LayuiData layuiData = new LayuiData();
		layuiData.setCode(0);
		List<TbFeedback> list = feedbackMapper.queryfeedback(start,pageSize);
		int count = feedbackMapper.queryfeedbackCount();
		layuiData.setCount(count);
		layuiData.setData(list);
		return layuiData;
	}


	@Override
	public HashMap<String, Object> showPieComboStatistics() {
		HashMap<String, Object> statisticsMap = new HashMap<>();
//      获取用户数量
		List<TbCombo> tbComboList = comboMapper.queryComboNameList();
		List legendList = new ArrayList();
		for (TbCombo tbCombo : tbComboList) {
			legendList.add(tbCombo.getComboName());
		}
		statisticsMap.put("legendList", legendList);
		System.out.println("legendList="+legendList);

		List<PieStatisticsData> outPieDataList = comboMapper.queryComboMoney();
		List<PieStatisticsData> inPieDataList = new ArrayList<>();
//		饼图中心只有三个
		for (int i=0;i<3;i++) {
			if (i==0){
				outPieDataList.get(i).setSelected(true);
			}
			inPieDataList.add(outPieDataList.get(i));
		}
		System.out.println("inPieDataList="+inPieDataList);
		statisticsMap.put("inPieDataList",inPieDataList);
		statisticsMap.put("outPieDataList", outPieDataList);
		System.out.println("outPieDataList="+outPieDataList);
		return statisticsMap;
	}
}
