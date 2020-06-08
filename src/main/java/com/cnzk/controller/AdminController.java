package com.cnzk.controller;

import com.alibaba.fastjson.JSON;

import com.cnzk.pojo.*;
import com.cnzk.service.AdminService;

import com.cnzk.service.MenuService;
import com.cnzk.service.RoleServeice;
import com.cnzk.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/AdminController")
public class AdminController
{
	@Autowired
	private AdminService adminService;
	@Resource
	private MenuService menuService;
	@Resource
	private RoleServeice roleServeice;


	private char[] codeSequence = { 'A', '1','B', 'C', '2','D','3', 'E','4', 'F', '5','G','6', 'H', '7','I', '8','J',
			'K',   '9' ,'L', '1','M',  '2','N',  'P', '3', 'Q', '4', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z'};

	//登陆l
	@RequestMapping(value = "/adminlogin",produces = { "application/json;charset=UTF-8"})
	@ResponseBody
	public String adminLogin(@RequestParam Map<String,Object> param, HttpSession session)  {
		System.out.println("===============================管理员登陆=============================");
		String vcode = session.getAttribute("vcode").toString();//获取session上的验证码
		System.out.println("验证码："+vcode);
//		if(vcode.equalsIgnoreCase(param.get("adminvcode").toString())){
			return adminService.adminlogin(param,session);//获取service层返回的信息
//		}
//		return "验证码错误";
	}
	//验证码
	/**
	 * 获取验证码图片
	 * @param session
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/CheckCodeServlet")
	public void CheckCodeServlet(HttpSession session, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("=====================获取验证码=======================");
		int width = 75;
		int height = 37;
		Random random = new Random();
		//设置response头信息
		//禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		//生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		//产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		//Graphics类的样式
		g.setColor(this.getColor(200, 250));
		g.setFont(new Font("Times New Roman",0,28));
		g.fillRect(0, 0, width, height);
		//绘制干扰线
		for(int i=0;i<40;i++){
			g.setColor(this.getColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		//绘制字符
		String strCode = "";
		for(int i=0;i<4;i++){
			String rand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			strCode = strCode + rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 15*i+6, 28);
		}
		//将字符保存到session中用于前端的验证
		session.setAttribute("vcode", strCode.toLowerCase());
		g.dispose();

		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
	}

	public  Color getColor(int fc,int bc){
		Random random = new Random();
		if(fc>255)
		{fc = 255;}
		if(bc>255)
		{	bc = 255;}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r,g,b);
	}



	@ResponseBody
	@RequestMapping("queryAdmin")
	public Object queryAdmin(String msg,String page,String limit){
		System.out.println("带条件获取管理员列表");
		Admin admin=new Admin();
		if(msg!=null&&!"".equals(msg.trim())){
			admin= JSON.parseObject(msg,Admin.class);
		}
		//获取页码;
		int startPage=Integer.parseInt(page);
		//每页数量
		int pageSize=Integer.parseInt(limit);
		//计算出起始查询位置
		int start = (startPage-1)*pageSize;
		LayuiData layuiData=adminService.queryAdmin(admin,start,pageSize);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}


	//查角色列表
	@ResponseBody
	@RequestMapping("queryRolesList")
	public Object queryRolesList(String page,String limit,String roleName,HttpServletRequest request, HttpServletResponse response){
		Admin admin = (Admin) request.getSession().getAttribute("tbAdmin");
		Integer roleId = admin.getRoleId();

		//存带有值得条件
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("roleId",roleId);

		//获取页码
		int startPage;
		//每页数量
		int pageSize;
		//计算出起始查询位置
		int start;

		if (null != limit && !"".equals(limit.trim()) && !"0".equals(limit)) {
			pageSize = Integer.valueOf(limit);
			condition.put("pageSize", pageSize);
		} else {
			pageSize = 10;
			condition.put("pageSize", pageSize);
		}
		if (null != page && !"".equals(page.trim()) && !"0".equals(page)) {
			startPage=Integer.parseInt(page);
			start = (startPage - 1) * pageSize;
			condition.put("start", start);
		} else {
			start = 0;
			condition.put("start", start);
		}

		condition.put("roleName",roleName);

		LayuiData layuiData=roleServeice.queryRolesList(condition);
		System.out.println("layuiData = " + JSON.toJSONString(layuiData));
		return layuiData;
	}

    //  查权限
    @RequestMapping("/queryMenuTree")
    @ResponseBody
    public void queryMenuTree(TbRole tbRole,HttpServletRequest request, HttpServletResponse response){
		List<MenuVo> treeDataList= menuService.menuAuthority(Integer.valueOf(tbRole.getRoleId()+""));
		if(treeDataList.size() != 0){
			ResponseUtils.outJson(response, treeDataList);
		}else{
			ResponseUtils.outHtml(response,"error");
		}
    }

	//  修改权限
	@RequestMapping("/updateMenuTree")
	@Transactional
	@ResponseBody
	public void updateMenuTree(HttpServletRequest request, HttpServletResponse response){
		Integer roleId = Integer.valueOf(request.getParameter("roleId"));
		String treeStr = request.getParameter("checkData");
		Integer row = roleServeice.updateMenuTree(treeStr,roleId);
		if (row != 0) {
			ResponseUtils.outHtml(response,"success");
		} else {
			ResponseUtils.outHtml(response,"error");
		}
	}

}
