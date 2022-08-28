package com.wookie_soft.covid.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "address")
    public String address ;         // 주소
    @ColumnInfo(name = "centerName")
    public String centerName ; // 시설 타입
    @ColumnInfo(name = "facilityName")
    public String facilityName ;  // 시설 이름
    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber ; //폰번호
    @ColumnInfo(name = "updatedAt")
    public String updatedAt ; // 업데이트 날짜
    @ColumnInfo(name = "lat")
    public String lat ;     // 위도
    @ColumnInfo(name = "lng")
    public String lng ;     // 경도

    public Data(String address, String centerName, String facilityName, String phoneNumber, String updatedAt, String lat, String lng) {
        this.address = address;
        this.centerName = centerName;
        this.facilityName = facilityName;
        this.phoneNumber = phoneNumber;
        this.updatedAt = updatedAt;
        this.lat = lat;
        this.lng = lng;
    }



}
