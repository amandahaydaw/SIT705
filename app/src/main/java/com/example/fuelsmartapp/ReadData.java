package com.example.fuelsmartapp;

public class ReadData {
    private double Site_Latitude;//month
    private double Site_Longitude;//rainfall
    private String Site_Brand;//sumHours

    public double getSite_Latitude() {
        return Site_Latitude;
    }

    public void setSite_Latitude(double Site_Latitude) {
        this.Site_Latitude = Site_Latitude;
    }

    public double getSite_Longitude() {
        return Site_Longitude;
    }

    public void setSite_Longitude(double Site_Longitude) {

        this.Site_Longitude = Site_Longitude;
    }

    public String getSite_Brand() {
        return Site_Brand;
    }

    public void setSite_Brand(String Site_Brand) {
        this.Site_Brand = Site_Brand;
    }

//    @Override
//    public String toString() {
////        return "WeatherSample{" +
////                "Site_Latitude='" + Site_Latitude + '\'' +
////                ", Site_Longitude=" + Site_Longitude +
////                ", Site_Brand=" + Site_Brand +
////                '}';
//        return Site_Latitude;
//
//    }
@Override
public String toString() {

    return Site_Latitude +Site_Longitude +Site_Brand;

}
}
