package by.epam.training.jwd.godot.service;


import by.epam.training.jwd.godot.service.impl.CoffeeServiseImpl;
import by.epam.training.jwd.godot.service.impl.OrderServiceImpl;
import by.epam.training.jwd.godot.service.impl.SpotsServiceImpl;
import by.epam.training.jwd.godot.service.impl.UserServiceImpl;

public final class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider(); 

	private ServiceProvider() {}
	
	private final UserService userService = new UserServiceImpl();
	private final CoffeeService coffeeService = new CoffeeServiseImpl();
	private final OrderService orderService = new OrderServiceImpl();
	private final SpotsService spotsService = new SpotsServiceImpl();

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}
	public CoffeeService getCoffeeService() {return coffeeService;}
	public OrderService getOrderService() {return orderService;}
	public SpotsService getSpotsService() {return spotsService;}

}
