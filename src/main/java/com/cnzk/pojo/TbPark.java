package com.cnzk.pojo;


public class TbPark {

  private long parkId;
  private String parkName;
  private String parkState;
  private String carNum;
  private long coordinateId;

  private TbCoordinate tbCoordinate;

	public TbPark()
	{
	}

	public TbPark(long parkId, String parkName, String parkState, String carNum, long coordinateId)
	{
		this.parkId = parkId;
		this.parkName = parkName;
		this.parkState = parkState;
		this.carNum = carNum;
		this.coordinateId = coordinateId;
	}

	public TbPark(long parkId, String parkState, String carNum)
	{
		this.parkId = parkId;
		this.parkState = parkState;
		this.carNum = carNum;
	}

	public long getParkId() {
    return parkId;
  }

  public void setParkId(long parkId) {
    this.parkId = parkId;
  }


  public String getParkName() {
    return parkName;
  }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }


    public String getParkState() {
        return parkState;
    }

    public void setParkState(String parkState) {
        this.parkState = parkState;
    }


    public String getCarNum() {
        return carNum;
    }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }


  public long getCoordinateId() {
    return coordinateId;
  }

  public void setCoordinateId(long coordinateId) {
    this.coordinateId = coordinateId;
  }

	@Override
	public String toString()
	{
		return "TbPark{" + "parkId=" + parkId + ", parkName='" + parkName + '\'' + ", parkState='" + parkState + '\'' + ", carNum='" + carNum + '\'' + ", coordinateId=" + coordinateId + '}';
	}

    public TbCoordinate getTbCoordinate() {
        return tbCoordinate;
    }

    public void setTbCoordinate(TbCoordinate tbCoordinate) {
        this.tbCoordinate = tbCoordinate;
    }
}
