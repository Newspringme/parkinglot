package com.cnzk.pojo;

/**
 * @author su
 * @date 2020/6/5-15:25
 */
public class Admin
{
	private int adminId;
	private String adminName;
	private String adminPass;
	private String adminSex;
	private String adminTel;
	private String headImg;
	private String adminState;
	private int roleId;
	private String roleName;
	private String regTime;
	private String adminFace;
	private String startTime;
	private String endTime;


	public Admin()
	{
	}


	public String getAdminState()
	{
		return adminState;
	}

	public Admin(int adminId, String adminName, String adminPass, String adminSex, String adminTel, String headImg, String adminState, int roleId, String roleName, String regTime, String adminFace, String startTime, String endTime)
	{
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPass = adminPass;
		this.adminSex = adminSex;
		this.adminTel = adminTel;
		this.headImg = headImg;
		this.adminState = adminState;
		this.roleId = roleId;
		this.roleName = roleName;
		this.regTime = regTime;
		this.adminFace = adminFace;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public void setRegTime(String regTime)
	{
		this.regTime = regTime;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public void setAdminState(String adminState)
	{
		this.adminState = adminState;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRegTime()
	{
		return regTime;
	}

	public String getAdminFace()
	{
		return adminFace;
	}

	public void setAdminFace(String adminFace)
	{
		this.adminFace = adminFace;
	}

	public int getAdminId()
	{
		return adminId;
	}

	public void setAdminId(int adminId)
	{
		this.adminId = adminId;
	}

	public String getAdminName()
	{
		return adminName;
	}

	public void setAdminName(String adminName)
	{
		this.adminName = adminName;
	}

	public String getAdminPass()
	{
		return adminPass;
	}

	public void setAdminPass(String adminPass)
	{
		this.adminPass = adminPass;
	}

	public String getAdminSex()
	{
		return adminSex;
	}

	public void setAdminSex(String adminSex)
	{
		this.adminSex = adminSex;
	}

	public String getAdminTel()
	{
		return adminTel;
	}

	public void setAdminTel(String adminTel)
	{
		this.adminTel = adminTel;
	}

	public String getHeadImg()
	{
		return headImg;
	}

	public void setHeadImg(String headImg)
	{
		this.headImg = headImg;
	}
}
