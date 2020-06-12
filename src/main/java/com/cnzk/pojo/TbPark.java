package com.cnzk.pojo;


public class TbPark {

    private Integer parkId;
    private String parkName;
    private String parkState;
    private String carNum;
    private Integer coordinateId;

    private TbCoordinate tbCoordinate;

    public TbCoordinate getTbCoordinate() {
        return tbCoordinate;
    }

    public void setTbCoordinate(TbCoordinate tbCoordinate) {
        this.tbCoordinate = tbCoordinate;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
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


    public Integer getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Integer coordinateId) {
        this.coordinateId = coordinateId;
    }

}
