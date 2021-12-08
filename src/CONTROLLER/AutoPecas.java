package CONTROLLER;

import VIEW.LoginVIEW;
import java.sql.SQLException;

public class AutoPecas {

    public static void main(String[] args) throws SQLException {

        LoginVIEW login = new LoginVIEW();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }
}
