/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ejb.Stateless;

/**
 *
 * @author tassyosantana
 */
@Stateless
public class Banco {

   private String driver ="com.mysql.jdbc.Driver";
    private String url ="jdbc:mysql://localhost:3306/gestor_financas_development";
    private String user = "root";
    private Connection conn;

    public Banco() throws SQLException, ClassNotFoundException{
        Class.forName(driver);
        conn = (Connection) DriverManager.getConnection(url,user,"root");
    }

    public Connection getConn(){
        return conn;
    }

    public void fecharConexao() throws SQLException{
        conn.close();
    }
}
