package by.epam.training.jwd.godot.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.training.jwd.godot.controller.command.impl.action.*;
import by.epam.training.jwd.godot.controller.command.impl.redirect.*;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.LOGINATION, new SigningIn());
		commands.put(CommandName.GOTOLOGINATIONPAGE, new GoToLoginationPage());
		commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPage());
		commands.put(CommandName.REGISTRATION, new Registration());
		commands.put(CommandName.GOTOINDEXPAGE, new GoToMainPage());
		commands.put(CommandName.LOGOUT, new Logout());
		commands.put(CommandName.GOTOADMINPAGE, new GoToAdminPage());
		commands.put(CommandName.GOTOMANAGEINGREDIENTSPAGE, new GoToIngredientManagementPage());
		commands.put(CommandName.MANAGEINGREDIENTS, new IngredientsManager());
		commands.put(CommandName.SWITCHLANGUAGE, new LanguageSwitcher());
		commands.put(CommandName.GOTOMANAGEUSERSPAGE, new GoToUsersManagementPage());
		commands.put(CommandName.MANAGEUSERS, new UsersManager());
		commands.put(CommandName.CREATEORDER, new OrderPositionCreator());
		commands.put(CommandName.GOTOPROFILEPAGE, new GoToProfilePage());
		commands.put(CommandName.EDITPROFILE, new ProfileAction());
		commands.put(CommandName.GOTOCART, new GoToCartPage());
		commands.put(CommandName.GOTOMANAGESPOTSPAGE, new GoToSpotsManagementPage());
		commands.put(CommandName.MANAGESPOT, new SpotsManager());
		commands.put(CommandName.GOTOBUYINGREDIENTS, new GoToBuyIngredientsPage());
		commands.put(CommandName.BUYINGREDIENT, new IngredientsPurchaseManager());
		commands.put(CommandName.CARTMANAGER, new CartManager());
		commands.put(CommandName.CHECKORDER, new OrderStatusChecker());
		commands.put(CommandName.GOTOCHECKORDER, new GoToCheckOrderPage());
		commands.put(CommandName.GOTOMANAGEORDERS, new GoToManageOrders());
		commands.put(CommandName.MANAGEORDERS, new OrdersManager());
	}

	
	public Command takeCommand(String name) {
		CommandName commandName;
		
		commandName = CommandName.valueOf(name.toUpperCase());
		
		return commands.get(commandName);
	}

}
