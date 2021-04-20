package by.epam.training.jwd.godot.dao.util;

import by.epam.training.jwd.godot.bean.delivery_point.Address;
import by.epam.training.jwd.godot.bean.delivery_point.Spot;
import by.epam.training.jwd.godot.bean.user.User;
import by.epam.training.jwd.godot.bean.user.UserRole;
import by.epam.training.jwd.godot.dao.constant.AddressTable;
import by.epam.training.jwd.godot.dao.constant.CoveredCitiesTable;
import by.epam.training.jwd.godot.dao.constant.CoveredRegionsTable;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.training.jwd.godot.dao.constant.AddressTable.ID;
import static by.epam.training.jwd.godot.dao.constant.CoffespotTable.BALANCE;
import static by.epam.training.jwd.godot.dao.constant.CoffespotTable.RATING;
import static by.epam.training.jwd.godot.dao.constant.UserTable.*;

public class SpotDataConverter {

    public Spot resultSetToSpot(ResultSet rs) throws SQLException {

            long uid = rs.getLong(ID);
            double balance = rs.getDouble(BALANCE);
            double rating = rs.getDouble(RATING);

            String region = rs.getString(CoveredRegionsTable.REGION);
            String region_ru = rs.getString(CoveredRegionsTable.REGION_RU);
            String city = rs.getString(CoveredCitiesTable.CITY);
            String city_ru = rs.getString(CoveredCitiesTable.CITY_RU);
            String street = rs.getString(AddressTable.STREET);
            String street_ru = rs.getString(AddressTable.STREET_RU);
            String house = rs.getString(AddressTable.HOUSE);
            Address address = new Address(region, region_ru, city, city_ru, street, street_ru, house);

            return new Spot(uid, rating, balance, address);


    }
}
