/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author tassyosantana
 */
@Stateless
public class FrequenciaDAO {
    private Banco banco;
    private Statement stmt;

    public void iniciaBanco() throws SQLException, ClassNotFoundException{
        banco = new Banco();
        stmt = (Statement) banco.getConn().createStatement();
    }

    public ArrayList<Frequencia> getAllFrequencia() throws SQLException, ClassNotFoundException{
        this.iniciaBanco();
        ArrayList<Frequencia> frequencias = new ArrayList<Frequencia>();
        String query = "SELECT id, descricao FROM frequencias order by id asc;";

        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            frequencias.add(new Frequencia(rs.getInt("id"),rs.getString("descricao")));
        }
        banco.fecharConexao();
        return frequencias;
    }

    public String buscaFrequenciaPorId(Integer id) throws SQLException, ClassNotFoundException{
        this.iniciaBanco();
        String descricao ="";

        ResultSet rs = stmt.executeQuery("select descricao from frequencias where id =" + id);

        while (rs.next()){
            descricao = rs.getString("descricao");
        }
        banco.fecharConexao();
        return descricao;
    }

   
}
