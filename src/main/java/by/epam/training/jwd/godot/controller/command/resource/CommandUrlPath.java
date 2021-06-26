package by.epam.training.jwd.godot.controller.command.resource;

public interface CommandUrlPath {

    String MAINPAGE = "jsp/main_page.jsp";
    String ERROR_PAGE = "error.jsp";
    String LOGINATIONPAGE = "jsp/logination.jsp";
    String REGISTRATIONPAGE = "jsp/registration.jsp";
    String ADMIN_PAGE = "jsp/admin_page.jsp";
    String INGREDIENTS_MANAGE_PAGE = "jsp/ingredients_manager.jsp";
    String SPOTS_MANAGE_PAGE = "jsp/admin/spots_manager.jsp";
    String USERS_MANAGE_PAGE = "jsp/users_manager.jsp";
    String PROFILE_PAGE = "jsp/user/profile.jsp";
    String CART_PAGE = "jsp/user/cart.jsp";
    String BUY_INGREDINTS = "jsp/admin/buy_ingredornts_page.jsp";
    String RECEPITS_MANAGER_PAGE = "jsp/admin/recepits_management/recepits_manager.jsp";
    String CHECK_ORER_PAGE = "jsp/user/check_order.jsp";
    String ORDER_MANAGEMENT = "jsp/admin/order_management/order_management.jsp";
    String ORDER_ARCHIVE = "jsp/admin/order_archive/order_archive.jsp";

    String GOTOINDEXPAGE_WITH_MSG = "Controller?command=gotoindexpage&message=%S";
    String GOTOINDEXPAGE = "Controller?command=gotoindexpage";
    String GOTOLOGINPAGE_WITH_MSG = "Controller?command=gotologinationpage&message=%s";
    String GOTOREGISTRATIONPAGE_WITH_MSG = "Controller?command=gotoregistrationpage&message=%s";
    String GOTOADMINPAGE = "Controller?command=gotoadminpage";
    String GOTOBUYINGREDIENTSPAGE = "Controller?command=gotobuyingredients&message=%s";
    String GOTOERRORPAGE = "Controller?command=gotoerrorpage";
    String GOTOCHECKORDERPAGE = "Controller?command=gotocheckorder";
}
