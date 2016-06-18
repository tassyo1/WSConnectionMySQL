/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import bd.Frequencia;
import bd.FrequenciaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author tassyosantana
 */
@WebService(serviceName = "FrequenciaWebService")
public class FrequenciaWebService {

    @EJB
    private FrequenciaDAO ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getAllFrequencia")
    public ArrayList<Frequencia> getAllFrequencia() throws SQLException, ClassNotFoundException {
        return ejbRef.getAllFrequencia();
    }

    @WebMethod(operationName = "buscaFrequenciaPorId")
    public String buscaFrequenciaPorId(@WebParam(name = "id") Integer id) throws SQLException, ClassNotFoundException {
        return ejbRef.buscaFrequenciaPorId(id);
    }
    
}
