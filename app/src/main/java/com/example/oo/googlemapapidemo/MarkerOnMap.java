package com.example.oo.googlemapapidemo;
import java.io.Serializable;

/**
 * Created by Judyza on 7/27/16 AD.
 */
public class MarkerOnMap implements Serializable {

    private String buildingName;
    private String address;
    private String tel;
    private String fax;
    private String website;
    private String openTime;
    private String photo;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    private Double lat;
    private Double lng;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }




    public MarkerOnMap(String photo, String openTime, String website, String fax, String tel, String address, String buildingName,Double lat,Double lng) {
        this.photo = photo;
        this.openTime = openTime;
        this.website = website;
        this.fax = fax;
        this.tel = tel;
        this.address = address;
        this.buildingName = buildingName;
        this.lat=lat;
        this.lng=lng;
    }
    public MarkerOnMap(){

    }


}
