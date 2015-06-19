package com.thirdpart.model.entity;
/**
 * 
 *  “longtitude”: “123.44”,

                     “latitude”: “234.55”

                      “ssid”: “publicwifi”,

                      “psk”: “password”,

                      “tag”: “kfc|home”,

                       “share”: “yes|no”

 * */
public class WifiData {

private String longtitude;
private String latitude;
private String ssid;
private String psk;
private String tag;
private String share;
public String getLongtitude() {
	return longtitude;
}
public void setLongtitude(String longtitude) {
	this.longtitude = longtitude;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getSsid() {
	return ssid;
}
public void setSsid(String ssid) {
	this.ssid = ssid;
}
public String getPsk() {
	return psk;
}
public void setPsk(String psk) {
	this.psk = psk;
}
public String getTag() {
	return tag;
}
public void setTag(String tag) {
	this.tag = tag;
}
public String getShare() {
	return share;
}
public void setShare(String share) {
	this.share = share;
}



}
