
package ws;

import bd.Categoria;
import bd.CategoriaDAO;
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
@WebService(serviceName = "CategoriaWebService")
public class CategoriaWebService {

    @EJB
    private CategoriaDAO ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "iniciaBanco")
    public void iniciaBanco() throws SQLException, ClassNotFoundException {
        ejbRef.iniciaBanco();
    }

    @WebMethod(operationName = "inserir")
    public String inserirCat(@WebParam(name = "tipo") String tipo, @WebParam(name = "nome") String nome, @WebParam(name = "frequencia_id") Integer frequencia_id, @WebParam(name = "data") String data, @WebParam(name = "valor") Float valor) throws ClassNotFoundException, SQLException, ParseException {
        return ejbRef.inserirCat(tipo, nome, frequencia_id, data, valor);
    }

    @WebMethod(operationName = "atualizar")
    public String atualizar(@WebParam(name = "id") Integer id, @WebParam(name = "tipo") String tipo, @WebParam(name = "nome") String nome, @WebParam(name = "frequencia_id") Integer frequencia_id, @WebParam(name = "data") String data, @WebParam(name = "valor") Float valor) throws ClassNotFoundException, SQLException, ParseException {
        return ejbRef.atualizar(id, tipo, nome, frequencia_id, data, valor);
    }

    @WebMethod(operationName = "deletar")
    public String deletar(@WebParam(name = "id") Integer id) throws SQLException, ClassNotFoundException {
        return ejbRef.deletar(id);
    }

    @WebMethod(operationName = "temMovimento")
    public boolean temMovimento(@WebParam(name = "id") Integer id) throws SQLException, ClassNotFoundException {
        return ejbRef.temMovimento(id);
    }

    @WebMethod(operationName = "listaTodasCategorias")
    public ArrayList<Categoria> listaTodasCategorias() throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.listaTodasCategorias();
    }

    @WebMethod(operationName = "buscaPorNome")
    public Categoria buscaPorNome(@WebParam(name = "nome") String nome) throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.buscaPorNome(nome);
    }

    @WebMethod(operationName = "validaNomeCategoria")
    public Boolean validaNomeCategoria(@WebParam(name = "nome") String nome, @WebParam(name = "id") Integer id) throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.validaNomeCategoria(nome, id);
    }

    @WebMethod(operationName = "buscaCategoriasEventuais")
    public ArrayList<Categoria> buscaCategoriasEventuais() throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.buscaCategoriasEventuais();
    }

    @WebMethod(operationName = "buscaCategoriasFrequentes")
    public ArrayList<Categoria> buscaCategoriasFrequentes() throws SQLException, ClassNotFoundException, ParseException {
        return ejbRef.buscaCategoriasFrequentes();
    }
    
}
