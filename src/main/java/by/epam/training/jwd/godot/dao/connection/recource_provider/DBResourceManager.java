package by.epam.training.jwd.godot.dao.connection.recource_provider;

import java.util.ResourceBundle;
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle =
            ResourceBundle.getBundle("db");

    private ResourceBundle testBundle =
            ResourceBundle.getBundle("testdb");

    public static DBResourceManager getInstance() {
        return instance;
    }

//    public String getValue(String key){
//        return bundle.getString(key);
//    }

    public String getValue(String key){
        return testBundle.getString(key);
    }
}
