package CONTROLLER;

import DAO.FuncionarioDAO;
import DAO.UsuarioDAO;
import VIEW.LoginVIEW;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoPecas {

    public static void main(String[] args) throws SQLException {

        LoginVIEW login = new LoginVIEW();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
    }
}
