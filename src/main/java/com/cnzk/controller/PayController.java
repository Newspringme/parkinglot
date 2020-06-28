package com.cnzk.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.cnzk.mapper.RatesMapper;
import com.cnzk.pojo.*;
import com.cnzk.service.*;
import com.cnzk.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PayController
{
	private final String APP_ID = "2016102800774945";
	private final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCrf7ME3iZ42rx0r89HkwMKSpaWuLLOvI73HkhOdUVd4uKy3WkjQ9b4d6Xl5uwj8oindBtDkW00qgUdPXzuKD/T4ko9AijFjO8aq7yYgF+6yxR4SQwvkQnrhSiuM0Xf9sArIPomVY2d7owKuTIHvakyhxIkc/AFxYdSlqBsHomTznp/Ff1JmjhVXNa+v8d2BiWJTQfNozumOsFpFGqCaRktM2l1FsjISNZsKjqv4FU0zmOnpdNUu4KZaBIFH5XUyZb6fIVY6g8w+ooDcF+ymyWGpyOo0sJvs7tiECorAeY4qMSv60bXNFUX+0ALwaD44R2DqWFTAZgVZi0m34OkHq+1AgMBAAECggEAJUuD9s8Itsd6rDV/ciU6SAaocOaBnAvNW1guiUFfcGdivVsA/jZGjYoXfcOWHssjhKbvNuZtRUKkEhPJE1GVb9KKKnJJw4w+gyJNxSxFpS18qhvYlo+IBLvUeO2ViHr0l2x4edWpEmtJ8RsNEjngjahp/Zto2xh1sTQodtq9OHMWwS1BU6F16Zivj25mJiX9iqBlDgAH76Hf2gmtCjxU71mlnH784iaXw4pPtIAaJEpH5MR0ymC/qwpG60NTtatvkYyZSuvl6ZHeIt1Rx7jP3SMOAU7Re73Mw3MEeaQSLzWmR4XFtHKX5+bIV5QrsSPOkxlImt3pLIrK22RNmc8GkQKBgQDtVD58nOSzDKNf2T5/YMgtwvO2M9h6j9ZARzeGSERhw7Rod7YBCLiH7yr5p8TYyNQKWQ4UGQspP6ue4MQ4MsPi5f3m/E330VetNwuRy3z3juci/jcTYpnDa5a9/j8vzd+1xkW0GRkIzI1KaMoP69+svZTymop7W/xZMqMmLXmtnwKBgQC4/aX0DJQC0nw6U0GFyrtHceQQ5KPG6dA3WCdYHz8+bFLe92ddmyU3YuDgDlybKpZUuck5LszWKDJkPdK58RJ4bV6YMPnEpfsZWrzO9jtJPFBdIVxVjXAJCmFtGNk0YeEHfbpQ0RHOuohS7UATbylAFFOX9aLLxxC+yoGcONe6KwKBgBWKG9G7e5Y0g29jjCSLaB+fHfdfZTpaa5uR2xw42tv0ox8IHmChpf3QWxole9wg/f2ib4CrdTPdL3pP8/8VvGmPbifUpk7jlQ2HfWlSCpl9QsUORXlg7Yuq7Bd6R8nS6YbZ4+GglYES74/dStl/EuHeovIRzJe9lX2S9vO0FOMJAoGAeSoBgViftT2CCn2vg98mTQRGud3Xe5h3PViq5GgK92rh2JI5DLFqbd2ApqAhfawmLLZHGwIRPg+z+b4YgJtqWqBf/dTOM0P3tEKfOftDF5O+m1EXdh2ejP1UlK1azplxV4O8/eQCAuJgGdi1SEVdwk/N9ND5YPkweZaPz+/XNGMCgYB4WFQn5Ly1ZoN/M0cFUDtQJEYkCx83PJPI/+RmmXiHmck1CbsvdWAAzTalnncs2PMOmv6YcanutJWIWWzISNmN+uXk9Ici6IBwu5uNWqjvry+T7iWwyrzB4ptOauz/HrtEBdntlz4+wzHwH+iiOTh09ztaSYgRoCY6xA8jzQw9rg==";
	private final String CHARSET = "UTF-8";
	private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtfS2TX8VFash0JpSZU5/7K2D4YCK+eG54ND5FkGUEMaTjbwQgU0C4lzy/6XWmJH2k+5IEpZechic1vX+IcHpkXO+5AqS18f18oyExS//CaPmEjK0J8vEKg9kauYHawiMlJx8LsLG8QAueaCHBAqKRAOELHJjG3WabQVgd902mtATBH1MphbFI/lsR4V43PeELcL8aHZAYJzhSFF+BUZ/UlLcQj8dgN5VlEcjffMouO2XyuntcrBixOpPzBzF3GwIAMN3jaJQp/hZHhlRvBVCw/0LiGj8GB68MGKbuA976bX+NkWIfXN/Gb7WFKbJVVoN6X5rIPgl2kAqIhJF9pjTQIDAQAB";
	//这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
	private final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
	private final String FORMAT = "JSON";
	//签名方式
	private final String SIGN_TYPE = "RSA2";
	//支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
	private final String NOTIFY_URL = "http://39.102.35.36:8080/parkinglot/exitcar";
	//    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
	private final String RETURN_URL = "http://localhost:8080/parkinglot/returnurl";
	//    private final String RETURN_URL = "http://localhost:8080/parkinglot/returnurl";
//    @Autowired
	@Resource
	private AdminService adminService;
	@Resource
	private BillService billService;
	@Resource
	private CarService carService;
	@Resource
	private ParkService parkService;
	@Resource
	private UserService userService;
	@Resource
	private ChackphotoService chackphotoService;

	@RequestMapping("alipay")
	public void alipay(HttpServletResponse httpResponse, String type, String carNum, String comboid) throws IOException, ParseException
	{
		Random r = new Random();
		//实例化客户端,填入所需参数
		AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		//在公共参数中设置回跳和通知地址
		request.setReturnUrl(RETURN_URL);
		request.setNotifyUrl(NOTIFY_URL);
		Map<String, String> map = null;
		switch (type)
		{
			case "car":
				map = carbill(type, carNum);
				break;
			case "card":
				map = cardbill(type, carNum, comboid);
				break;
			case "cardRenew":
				map = cardbill(type, carNum, comboid);
				break;
		}
		request.setBizContent("{\"out_trade_no\":\"" + map.get("out_trade_no") + "\"," + "\"total_amount\":\"" + map.get("total_amount") + "\"," + "\"subject\":\"" + map.get("subject") + "\"," + "\"body\":\"" + map.get("body") + "\"," + "\"timeout_express\":\"" + map.get("timeout_express") + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		String form = "";
		try
		{
			form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		}
		catch (AlipayApiException e)
		{
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@ResponseBody
	public Map carbill(String type, String carNum) throws ParseException
	{
		String username = chackphotoService.finduser(carNum);
		System.out.println("入场用户-----------" + username);
		//查询入场时间
		String entertime = chackphotoService.findentertime(carNum);
		//出厂时间
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		String exittime = sdf.format(new Date());//查车找人
//商户订单号，商户网站订单系统中唯一订单号，必填
		//生成随机Id
		String out_trade_no = App.getoutTradeNo();
		TbBill bill = new TbBill();
		//付款金额，必填
		Map<String, String> map = App.getBill(entertime, exittime, adminService.queryPrice());
		String total_amount = map.get("bill");
		//订单名称，必填
		String subject = "菜鸟停车场" + map.get("stopTime");
		String timeout_express = "5m";
		bill.setBillNum(out_trade_no);
		bill.setBillMoney(total_amount);
		bill.setCarNum(carNum);
		bill.setUserName(username);
		bill.setBillType(type);
		billService.insertBill(bill);
		//商品描述，可空
		String body = "菜鸟停车场自助缴费" + map.get("stopTime");
		Map<String, String> billmap = new HashMap<>();
		billmap.put("out_trade_no", out_trade_no);
		billmap.put("total_amount", total_amount);
		billmap.put("subject", subject);
		billmap.put("timeout_express", timeout_express);
		billmap.put("body", body);
		return billmap;
	}

	@ResponseBody
	public Map cardbill(String type, String carNum, String comboid)
	{
		String username = chackphotoService.finduser(carNum);
		//设置支付页参数
		String out_trade_no = App.getoutTradeNo();
		String subject = "菜鸟停车场月卡办理";
		String total_amount = adminService.queryComboValue(comboid);
		System.out.println("月卡费用" + total_amount);
		String body = "菜鸟停车场自助缴费";
		//生成订单
		TbBill bill = new TbBill();
		bill.setBillNum(out_trade_no);
		bill.setBillMoney(total_amount);
		bill.setComboId(Integer.valueOf(comboid));
		bill.setCarNum(carNum);
		bill.setUserName(username);
		bill.setBillType(type);
		billService.insertBill(bill);
		System.out.println("入场用户-----------" + username);

		Map<String, String> billmap = new HashMap<>();
		billmap.put("out_trade_no", out_trade_no);
		billmap.put("total_amount", total_amount);
		billmap.put("subject", subject);
		billmap.put("timeout_express", "5m");
		billmap.put("body", body);
		return billmap;
	}

	//测试在手机跳出app支付
	@ResponseBody
	@RequestMapping("appalipay")
	public void appalipay(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException
	{
		String out_trade_no = UUID.randomUUID().toString();
		// 订单名称，必填
		String subject = "菜鸟停车场";
		String timeout_express = "5m";
		// 付款金额，必填
		String total_amount = "11";        // 商品描述，可空
		String body = "菜鸟停车场";        // 超时时间 可空
		// 销售产品码 必填
		String product_code = "QUICK_WAP_WAY";
		/**********************/
		// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
		//调用RSA签名方式
		AlipayClient client = new DefaultAlipayClient(GATEWAY_URL, APP_ID, AlipayConfig.APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

		// 封装请求支付信息
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(out_trade_no);
		model.setSubject(subject);
		model.setTotalAmount(total_amount);
		model.setBody(body);
		model.setTimeoutExpress(timeout_express);
		model.setProductCode(product_code);
		alipay_request.setBizModel(model);
		// 设置异步通知地址
		alipay_request.setNotifyUrl(NOTIFY_URL);
		// 设置同步地址
		alipay_request.setReturnUrl(RETURN_URL);

		// form表单生产
		String form = "";
		try
		{
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			System.out.println(form);
			response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
			response.getWriter().write(form);//直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		}
		catch (AlipayApiException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping("exitcar")
	public void exitcar(HttpServletRequest request, HttpServletResponse Response) throws IOException, AlipayApiException, ParseException
	{
//获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); )
		{
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++)
			{
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");

		if (verify_result)
		{//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED"))
			{
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			}
			else if (trade_status.equals("TRADE_SUCCESS"))
			{
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//如果有做过处理，不执行商户的业务程序

				TbBill bill = new TbBill();
				bill.setBillNum(out_trade_no);

				TbBill billState = billService.isSucceed(bill);
				if (billState.getBillState() == 0)
				{
					switch (billState.getBillType())
					{
						case "car":
							//付款完成
							billService.updateBill(bill);
							bill = billService.getCarNum(bill);
							//车位清空
							parkService.carExit(bill);
							//车出库
							userService.carexit(bill.getCarNum());
							break;
						case "card":
							billService.updateBill(bill);
							bill = billService.getCarNum(bill);
							handlePackage(bill.getCarNum(), bill.getComboId() + "");
							break;
						case "cardRenew":
							billService.updateBill(bill);
							bill = billService.getCarNum(bill);
							handleRenew(bill.getCarNum(), bill.getComboId() + "");
							break;
					}

				}
			}
			//注意：
			//如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//            out.clear();
//            out.println("success");	//请不要修改或删除
			Response.getWriter().write("success");
			//////////////////////////////////////////////////////////////////////////////////////////
		}
		else
		{//验证失败

			Response.getWriter().write("fail");
//            out.println("fail");
		}
	}


	@ResponseBody
	@RequestMapping("returnurl")
	public void returnurl(HttpServletRequest request, HttpServletResponse Response) throws IOException, AlipayApiException, ParseException
	{
		//获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); )
		{
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++)
			{
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");

		if (verify_result)
		{//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			//该页面可做页面美工编辑
			TbBill bill = new TbBill();
			bill.setBillNum(out_trade_no);
			TbBill billState = billService.isSucceed(bill);
			if (billState.getBillState() == 0)
			{
				switch (billState.getBillType())
				{
					case "car":
						//付款完成
						billService.updateBill(bill);
						bill = billService.getCarNum(bill);
						//车位清空
						parkService.carExit(bill);
						//车出库
						userService.carexit(bill.getCarNum());
						break;
					case "card":
						billService.updateBill(bill);
						bill = billService.getCarNum(bill);
						handlePackage(bill.getCarNum(), bill.getComboId() + "");
						break;
					case "cardRenew":
						billService.updateBill(bill);
						bill = billService.getCarNum(bill);
						handleRenew(bill.getCarNum(), bill.getComboId() + "");
						break;
				}

			}
			if (WebSocket.electricSocketMap.get("ip") != null)
			{
				for (Session session : WebSocket.electricSocketMap.get("ip"))
				{
					session.getBasicRemote().sendText("carexit," + bill.getCarNum());
				}
			}
//            out.clear();
//            out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			Response.sendRedirect("http://localhost:8080/parkinglot/url/close");
			//////////////////////////////////////////////////////////////////////////////////////////
		}
		else
		{
			//该页面可做页面美工编辑
//            out.clear();
//            out.println("验证失败");
		}
	}

	//办理月缴
	@ResponseBody
	public Object handlePackage(String carNum, String comboId)
	{
		TbCar tbCar = new TbCar();
		tbCar.setCarNum(carNum);
		tbCar.setComboId(Integer.parseInt(comboId));
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (tbCar.getComboId())
		{
			case 1:
				calendar.add(Calendar.MONTH, 1);
				break;
			case 2:
				calendar.add(Calendar.MONTH, 3);
				break;
			case 3:
				calendar.add(Calendar.MONTH, 6);
				break;
			case 4:
				calendar.add(Calendar.MONTH, 12);
				break;
			case 5:
				calendar.add(Calendar.MONTH, 36);
				break;
			default:
				break;
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		tbCar.setEndTime(sd.format(calendar.getTime()));
		int num = carService.handlePackage(tbCar);
		boolean bool = false;
		if (num > 0)
		{
			bool = true;
			System.out.println("办理成功");

		}
		return bool;
	}
	//  办理续费
	@ResponseBody
	public Object
	handleRenew(String carNum,String comboId) throws ParseException
	{
		int addTime=0;
		TbCar tbCar = carService.queryCarByCarNum(carNum);
		if(tbCar==null){
			return "isNull";
		}else {
			switch(comboId){
				case "2":
					  addTime=3;
				break;
				case "3":
					  addTime=6;
				break;
				case "4":
					  addTime=12;
				break;
				case "5":
					  addTime=36;
				break;
				default:
				break;
			}
			String str = tbCar.getEndTime();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sd.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, addTime);
			tbCar.setEndTime(sd.format(calendar.getTime()));
			int num = carService.handleRenew(tbCar);
			boolean bool = false;
			if (num > 0)
			{
				bool = true;
				System.out.println(" 续费办理成功");
			}
			return bool;
		}
	}
}
