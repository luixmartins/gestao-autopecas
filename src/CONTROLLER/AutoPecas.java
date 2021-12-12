package CONTROLLER;

import VIEW.LoginVIEW;
import java.sql.SQLException;

import java.text.ParseException;

public class AutoPecas {

    public static void main(String[] args) throws SQLException, ParseException {

        LoginVIEW login = new LoginVIEW();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }
}
