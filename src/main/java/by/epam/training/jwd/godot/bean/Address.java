package by.epam.training.jwd.godot.bean;

import by.epam.training.jwd.godot.bean.coffee.Coffee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String region;
    private String regionRu;
    private String city;
    private String cityRu;
    private String street;
    private String streetRu;
    private String house;

    public Address(){}

    public Address(String region, String regionRu, String city, String cityRu, String street, String streetRu, String house) {
        this.region = region;
        this.regionRu = regionRu;
        this.city = city;
        this.cityRu = cityRu;
        this.street = street;
        this.streetRu = streetRu;
        this.house = house;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionRu() {
        return regionRu;
    }

    public void setRegionRu(String regionRu) {
        this.regionRu = regionRu;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityRu() {
        return cityRu;
    }

    public void setCityRu(String cityRu) {
        this.cityRu = cityRu;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetRu() {
        return streetRu;
    }

    public void setStreetRu(String streetRu) {
        this.streetRu = streetRu;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getRegion(), address.getRegion()) &&
                Objects.equals(getRegionRu(), address.getRegionRu()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getCityRu(), address.getCityRu()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getStreetRu(), address.getStreetRu()) &&
                Objects.equals(getHouse(), address.getHouse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegion(), getRegionRu(), getCity(), getCityRu(), getStreet(), getStreetRu(), getHouse());
    }

    @Override
    public String toString() {
        return "Address{" +
                "region='" + region + '\'' +
                ", regionRu='" + regionRu + '\'' +
                ", city='" + city + '\'' +
                ", cityRu='" + cityRu + '\'' +
                ", street='" + street + '\'' +
                ", streetRu='" + streetRu + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
