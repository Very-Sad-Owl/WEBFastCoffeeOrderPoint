package by.epam.training.jwd.godot.dao;


import by.epam.training.jwd.godot.dao.impl.CoffeeDaoImpl;
import by.epam.training.jwd.godot.dao.impl.OrderDaoImpl;
import by.epam.training.jwd.godot.dao.impl.SpotsDaoImpl;
import by.epam.training.jwd.godot.dao.impl.UserDaoImpl;

public final class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();

	private final UserDao userdao = new UserDaoImpl();
	private final CoffeeDao coffeedao = new CoffeeDaoImpl();
	private final OrderDao orderDao = new OrderDaoImpl();
	private final SpotsDao spotsDao = new SpotsDaoImpl();

	private DaoProvider() {}
	
	public static DaoProvider getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userdao;
	}
	public CoffeeDao getCoffeeDao(){return coffeedao;}
	public OrderDao getOrderDao(){return orderDao;}
	public SpotsDao getSpotsDao(){return spotsDao;}

}
