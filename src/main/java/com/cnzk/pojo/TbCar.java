package com.cnzk.pojo;

/**
 * @author su
 * @date 2020/6/12-9:04
 */
public class TbCar
{
	private int carId;
	private String carNum;
	private String carColor;
	private String carType;
	private String carBrand;
	private int userId;
	private String userName;
	private String userTel;
	private int vipId;
	private int comboId;
	private String endTime;
	private int months;

	public TbCar()
	{
	}

	public TbCar(int carId, String carNum, String carColor, String carType, String carBrand, int userId, String userName, String userTel, int vipId, int comboId, String endTime, int months)
	{
		this.carId = carId;
		this.carNum = carNum;
		this.carColor = carColor;
		this.carType = carType;
		this.carBrand = carBrand;
		this.userId = userId;
		this.userName = userName;
		this.userTel = userTel;
		this.vipId = vipId;
		this.comboId = comboId;
		this.endTime = endTime;
		this.months = months;
	}

	public int getMonths()
	{
		return months;
	}

	public void setMonths(int months)
	{
		this.months = months;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public int getComboId()
	{
		return comboId;
	}

	public void setComboId(int comboId)
	{
		this.comboId = comboId;
	}

	public int getCarId()
	{
		return carId;
	}

	public void setCarId(int carId)
	{
		this.carId = carId;
	}

	public String getCarNum()
	{
		return carNum;
	}

	public void setCarNum(String carNum)
	{
		this.carNum = carNum;
	}

	public String getCarColor()
	{
		return carColor;
	}

	public void setCarColor(String carColor)
	{
		this.carColor = carColor;
	}

	public String getCarType()
	{
		return carType;
	}

	public void setCarType(String carType)
	{
		this.carType = carType;
	}

	public String getCarBrand()
	{
		return carBrand;
	}

	public void setCarBrand(String carBrand)
	{
		this.carBrand = carBrand;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserTel()
	{
		return userTel;
	}

	public void setUserTel(String userTel)
	{
		this.userTel = userTel;
	}

	public int getVipId()
	{
		return vipId;
	}

	public void setVipId(int vipId)
	{
		this.vipId = vipId;
	}

	@Override
	public String toString()
	{
		return "TbCar{" + "carId=" + carId + ", carNum='" + carNum + '\'' + ", carColor='" + carColor + '\'' + ", carType='" + carType + '\'' + ", carBrand='" + carBrand + '\'' + ", userId=" + userId + ", userName='" + userName + '\'' + ", userTel='" + userTel + '\'' + ", vipId=" + vipId + '}';
	}
}
