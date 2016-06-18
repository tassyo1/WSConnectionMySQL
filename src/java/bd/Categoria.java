/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;

/**
 *
 * @author tassyosantana
 */
@Stateless
public class Categoria {

    private Banco banco;
    private PreparedStatement pstmt;
    private Statement stmt;
    
    
    public String inserir(String tipo, String nome, Integer frequencia_id, String data, Float valor)
    throws ClassNotFoundException, SQLException{
        pstmt = banco.getConn().prepareStatement("insert into categorias (tipo, nome, frequencia_id, " +
                " data_agendada, valor) values (?,?,?,?,?");
        long resultado;

        pstmt.setString(1, tipo);
        pstmt.setString(2,nome);
        pstmt.setString(3, frequencia_id.toString());
        pstmt.setString(4,data);
        pstmt.setString(5,valor.toString());

        resultado = pstmt.executeUpdate();
        banco.fecharConexao();

        if (resultado == -1)
            return "Erro ao inserir registro";

        return "Registro inserido com sucesso";
    }
}
