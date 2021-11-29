/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luizh
 */
public class UsuarioDAO {

    PreparedStatement pst;

    public Usuario buscaLogin(int cod, String senha) throws SQLException {
        try {
            Usuario user = null;

            String sql = "select * from funcionario where idFuncionario = " + cod + " and senha "
                    + " = convert(\"" + senha + "\" using utf8mb4) COLLATE utf8mb4_bin";

            pst = Conexao.getInstance().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                user = new Usuario(rs.getInt("nivel_acesso"), rs.getInt("idFuncionario"),
                        rs.getString("senha"));
            }
            return user;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
