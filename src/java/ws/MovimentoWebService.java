/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import bd.Movimento;
import bd.MovimentoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author tassyosantana
 */
@WebService(serviceName = "MovimentoWebService")
public class MovimentoWebService {

    @EJB
    private MovimentoDAO ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "iniciaBanco")
    public void iniciaBanco() throws SQLException, ClassNotFoundException {
        ejbRef.iniciaBanco();
    }

    @WebMethod(operationName = "inserir")
    public String inserir(@WebParam(name = "data") String data, @WebParam(name = "saldo")
            Float saldo_atual, @WebParam(name = "categoria_id") Integer categoria_id) throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.inserir(data, saldo_atual, categoria_id);
    }

    @WebMethod(operationName = "buscaTodosMovimentos")
    public ArrayList<Movimento> buscaTodosMovimentos() throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.buscaTodosMovimentos();
    }

    @WebMethod(operationName = "buscaUltimoMovimento")
    public Movimento buscaUltimoMovimento() throws SQLException, ClassNotFoundException {
        return ejbRef.buscaUltimoMovimento();
    }

    @WebMethod(operationName = "buscaMovimentosPorCategoriaHoje")
    public Movimento buscaMovimentosPorCategoriaHoje(@WebParam(name = "categoria_id") int categoria_id) throws SQLException, ClassNotFoundException {
        return ejbRef.buscaMovimentosPorCategoriaHoje(categoria_id);
    }
    
}
