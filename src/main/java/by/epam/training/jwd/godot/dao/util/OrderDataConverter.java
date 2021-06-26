package by.epam.training.jwd.godot.dao.util;

import by.epam.training.jwd.godot.bean.order_element.Order;
import by.epam.training.jwd.godot.bean.order_element.OrderStatus;
import by.epam.training.jwd.godot.dao.constant.table_column.OrderTable;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.training.jwd.godot.dao.constant.table_column.OrderTable.*;


public class OrderDataConverter {

    private static final Logger LOGGER = Logger.getLogger(OrderDataConverter.class);

    public OrderDataConverter(){}

    public Order retrieveFromResultSet(ResultSet rs) throws SQLException {
        Order found = new Order();
        found.setUid(rs.getLong(OrderTable.ID));
        found.setStatus(OrderStatus.valueOf(rs.getString("status").toUpperCase()));
        found.setCoast(rs.getDouble(PRICE));
        found.setEstimatedTime(rs.getInt(ESTIMATED_TIME));
        found.setDate(new Date(rs.getTimestamp(DATE).getTime()));

        return found;
    }

}
