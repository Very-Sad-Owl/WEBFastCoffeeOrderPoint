package by.epam.training.jwd.godot.dao.constant;

import by.epam.training.jwd.godot.bean.coffee.IngredientType;

import static by.epam.training.jwd.godot.dao.constant.table_column.CoffeeTable.*;
import static by.epam.training.jwd.godot.dao.constant.table_column.CoffeeTable.INGREDIENT_TITLE;

public interface SQLQuery {

    //coffee
    String GET_ALL_QUERY = "SELECT * FROM %s";
    String GET_ALL_USERS_DATA = "select users.login, users.password, users.email, users.balance, roles.role_title, users.img_path from users join roles on users.role_id = roles.id";
    String GET_SEASONAL_INGREDIENTS = String.format(
            "select title, price, img_source, season, type from ( select title, price, img_source, type_id, season_id, seasons.season as season, ingredient_type.type as type from ingredients join seasons on ingredients.season_id = seasons.id join ingredient_type on ingredients.type_id = ingredient_type.id ) as aub where type_id = any(select id from ingredient_type where type in ('%s', '%s')) and season_id = any(select id from seasons where season in ('any', ?))",
            IngredientType.DECORATION, IngredientType.UNIVERSAL);
    String GET_COFFEE_SIZES = "SELECT %s, %s FROM %s WHERE %s" +
            " = any(SELECT %s from %s where %s = '%s')";
    String DELETE_INGREDIENT = String.format("DELETE FROM %s WHERE %s = ?", INGREDIENTS_TABLE, INGREDIENT_TITLE);
    String UPDATE_INGREDIENT = String.format("UPDATE %s " +
                    "SET %s = ?, %s = ?, " +
                    "%s = (select %s from %s where %s = ?), %s = ?, " +
                    "%s = (select %s from %s where %s = ?) " +
                    "WHERE %s = ?", INGREDIENTS_TABLE, INGREDIENT_TITLE, INGREDIENT_PRICE, INGREDIENT_TYPE_ID,
            INGREDIENTS_ID, INGREDIENT_TYPE_TABLE, INGREDIENT_TYPE_TITLE, INGREDIENT_IMG, INGREDIENT_SEASON_ID, SEASON_ID,
            SEASONS_TABLE, SEASON_TITLE, INGREDIENT_TITLE);
    String GET_INGREDIENTS = "SELECT ingredients.id, title, price, img_source, season, type FROM ingredients JOIN seasons on seasons.id = ingredients.season_id  JOIN ingredient_type on ingredient_type.id = ingredients.type_id";
    String INSERT_INGREDIENT = String.format(
            "INSERT INTO ingredients(%s, %s, %s, %s, %s) values(?, ?, (select id from %s where %s = ?), ?, (select id from %s where %s = ?))"
    , INGREDIENT_TITLE, INGREDIENT_PRICE, INGREDIENT_TYPE_ID, INGREDIENT_IMG, INGREDIENT_SEASON_ID, INGREDIENT_TYPE_TABLE, INGREDIENT_TYPE_TITLE, SEASONS_TABLE, SEASON_TITLE);
    String GET_BEVERAGE_INGREDIENTS = "select coffee_type_id, ingredient_id, amount, ingredients.title, ingredients.price, ingredients.img_source, (select type from ingredient_type where id = ingredients.type_id) as type, (select season from seasons where id = ingredients.season_id) as season from recepits join ingredients on recepits.ingredient_id = ingredients.id where coffee_type_id = (select id from coffee_types where title = \"%s\")";

    //Order
    String ADD_POSITION = "insert into order_positions(coffee_type_id, size_id, order_id) values ((select id from coffee_types where title = ?), (select id from sizes where size = ? and coffee_type_id = (select id from coffee_types where title = ?)), ?)";
    String ADD_ORDER_DECORS = "insert into order_decorations(order_positions_id, ingredient_id, amount) values";
    String DECOR_VALUES = "(%d, (select id from ingredients where title = '%s'), %d)";
    String INSERT_INTO_ORDER = "UPDATE order_positions SET order_id = ? WHERE id = ?";
    String GET_POSITION_DECORATIONS = "select price, amount, ingredients.img_source, season, ingredients.title, type from order_decorations join ingredients on order_decorations.ingredient_id = ingredients.id join seasons on seasons.id = season_id join ingredient_type on ingredient_type.id = type_id where order_positions_id = ?";
    String GET_POSITION_INGREDIENTS = "select ingredients.title, ingredients.price, ingredients.img_source, amount, seasons.season, ingredient_type.type from ingredients join recepits on ingredients.id = recepits.ingredient_id JOIN coffee_types on recepits.coffee_type_id = coffee_types.id JOIN seasons on seasons.id = ingredients.season_id JOIN ingredient_type on ingredients.type_id = ingredient_type.id where coffee_types.title = ?";
    String GET_CURRENT_ORDER = "select amount, order_positions.id, title, img_source, size, increment, price from order_positions join coffee_types on coffee_type_id = coffee_types.id join sizes on sizes.id = size_id where order_id = ?";
    String INSERT_EMPTY_ORDER = "insert into orders(user_id, status_id, estimated_time) values((select id from users where login = ?), (select id from order_statuses where status = ?), ?)";
    String GET_ORDER = "select * from orders join order_statuses on status_id = order_statuses.id where status_id = (select id from Order_statuses where status = ?) and user_id = (select id from users where login = ?)";
    String GET_ORDER_BY_UID = "select * from orders join order_statuses on status_id = order_statuses.id join users on orders.user_id = users.id join roles on users.role_id = roles.id where orders.id = ?";
    String CHECK_ORDER = "select id from orders where user_id = (select id from users where login = ?) and status_id = (select id from order_statuses where status = ?)";
    String GET_ORDER_POSITIONS = "select * from order_positions join coffee_types on coffee_type_id = coffee_types.id join sizes on size_id = sizes.id where order_id = ?";
    String SET_POSITION_AMOUNT = "update order_positions set amount = ? where id = ?";
    String DELETE_POSITIONS = "delete from order_positions where id not in(%s) and order_id = ?";
    String UPDATE_ORDER_TO_PLACED = "update orders set estimated_time = ?, date = ?, delivery_point_id = ?, payment_method_id = (select id from payment_method where method = ?), status_id = (select id from order_statuses where status = ?); ";
    String UPDATE_COAST = "update order_positions set price = (((select coast from coffee_types where id = coffee_type_id) + (select sum(price * amount) from order_decorations join ingredients on ingredients.id = ingredient_id)) * (select increment from sizes where id = size_id) * amount ) where order_id = ?";
    String UPDATE_ORDER_COAST = "update orders set price = (select sum(price) from order_positions where order_id = ?) where id = ?";
    String GET_ORDER_STATUS = "select status from order_statuses where id = (select status_id from orders where id = ?)";
    String GET_IN_PROCESS_ORDERS = "select * from orders join order_statuses on status_id = order_statuses.id join users on user_id = users.id join roles on users.role_id = roles.id where status_id in (select id from order_statuses where status in ('placed', 'preparing', 'ready'))";
    String GET_IN_PROCESS_ORDERS_BY_SPOT = "select * from orders join order_statuses on status_id = order_statuses.id join users on user_id = users.id join roles on users.role_id = roles.id where status_id in (select id from order_statuses where status in ('placed', 'preparing', 'ready')) and delivery_point_id = ?";
    String SET_POSITION_COAST = "update order_positions set price = (select (sum(ingr_price * ingr_amount) + coast) * increment from(\n" +
            "select order_positions.amount, order_positions.coffee_type_id, order_positions.size_id, \n" +
            "order_decorations.order_positions_id, order_decorations.ingredient_id, order_decorations.amount as ingr_amount,\n" +
            "ingredients.price as ingr_price,\n" +
            "sizes.increment as increment,\n" +
            "coffee_types.coast\n" +
            "from order_positions join order_decorations on order_decorations.order_positions_id = order_positions.id \n" +
            "join ingredients on ingredients.id = order_decorations.ingredient_id\n" +
            "join sizes on sizes.id = order_positions.size_id\n" +
            "join coffee_types on coffee_types.id = order_positions.coffee_type_id\n" +
            "where order_positions.id = ?) as sub) where id = ?";
    String SET_ORDER_STATUS = "update orders set status_id = (select id from order_statuses where status = ?) where id = ?";
    String SET_INCOME = "update coffee_spot set balance = balance + income_increment * (select price from orders where id = ?)";
    String SET_CONSUMPTION = "update coffee_spot set balance = balance - (select price from orders where id = ?)";
    String GET_ORDERS = "select * from orders join order_statuses on status_id = order_statuses.id join users on user_id = users.id join roles on users.role_id = roles.id";

    //spots
    String GET_SPOT = "select * from coffeespot join addresses on coffeespot.address_id = addresses.id join covered_regions on region_id = covered_regions.id join covered_cities on city_id = covered_cities.id where coffeespot.id = ?";
    String GET_ALL_COFFEE_SPOTS = "select * from coffeespot join addresses on coffeespot.address_id = addresses.id join covered_regions on region_id = covered_regions.id join covered_cities on city_id = covered_cities.id";
    String GET_ADDRESS = "select * from addresses where id = ?";
    String DELETE_SPOT = "delete from coffeespot where id = ?";
    String UPDATE_CITY = "UPDATE covered_cities SET city = ?, city_ru = ? WHERE id = (select city_id from addresses where id = ?)";
    String UPDATE_REGION = "UPDATE covered_regions SET region = ?, region_ru = ? WHERE id = (select region_id from addresses where id = ?)";
    String UPDATE_ADDR_LINE = "UPDATE addresses SET street = ?, street_ru = ?, house = ? WHERE id = ?";

    String GET_CITY = "select count(*) as count from covered_cities  where city = ?";
    String GET_REGION = "select count(*) as count from covered_regions  where region = ?";
    String INSERT_REGION = "insert into covered_regions(region, region_ru) values(?, ?)";
    String INSERT_CITY = "insert into covered_cities(city, city_ru, region_id) values(?, ?, (select id from covered_regions where region = ?))";
    String INSERT_ADDRESS = "insert into addresses(region_id, city_id, street, street_ru, house) values((select id from covered_regions where region = ?), (select id from covered_cities where city = ?), ?, ?, ?)";
    String INSERT_SPOT = "insert into coffeespot(address_id) values(?)";
    String GET_SPOT_INGREDIENTS = "select amount, ingredients.title, price, ingredients.img_source, season, ingredient_type.type from storage join ingredients on storage.ingredient_id = ingredients.id join seasons on seasons.id = ingredients.season_id join ingredient_type on ingredient_type.id = ingredients.type_id where coffeespot_id = ?";
    String GET_SPOT_INGREDIENT = "select amount, ingredients.title, price, ingredients.img_source, quantity, season, ingredient_type.type from storage join ingredients on storage.ingredient_id = ingredients.id join seasons on seasons.id = ingredients.season_id join ingredient_type on ingredient_type.id = ingredients.type_id where ingredients.title = ?";
    String INSERT_INGREDIENT_INTO_STOGARE = "insert into storage(coffeespot_id, ingredient_id, amount) values(?, (select id from ingredients where title = ?), ?)";
    String UPDATE_AMOUNT = "UPDATE storage SET amount = ? WHERE id = ? and ingredient_id = (select id from ingredients where title = ?)";

}
