package com.wookie_soft.covid.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
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

    public Data(int uid, String address, String centerName, String facilityName, String phoneNumber, String updatedAt, String lat, String lng) {
        this.uid = uid;
        this.address = address;
        this.centerName = centerName;
        this.facilityName = facilityName;
        this.phoneNumber = phoneNumber;
        this.updatedAt = updatedAt;
        this.lat = lat;
        this.lng = lng;
    }

    // 게터세터...

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getUid() {
        return uid;
    }

    public String getAddress() {
        return address;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
