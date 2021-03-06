
package bd;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author tassyosantana
 */
@Stateless
public class CategoriaDAO {
     private Banco banco;
    private PreparedStatement pstmt;
    private Statement stmt;
    SimpleDateFormat sf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
    
    public void iniciaBanco() throws SQLException, ClassNotFoundException{
        banco = new Banco();
        stmt = (Statement) banco.getConn().createStatement();
    }

    public String inserirCat(String tipo, String nome, Integer frequencia_id, String data, Float valor)
    throws ClassNotFoundException, SQLException, ParseException{
        this.iniciaBanco();
        pstmt = banco.getConn().prepareStatement("insert into categorias (tipo, nome, frequencia_id, " +
                " data_agendada, valor) values (?,?,?,?,?)");
        long resultado;

        pstmt.setString(1, tipo);
        pstmt.setString(2,nome);
        pstmt.setString(3, frequencia_id.toString());

        Date dt = sf1.parse(data);
        String nova = sf2.format(dt);
        pstmt.setString(4,nova);
        pstmt.setString(5,valor.toString());

        resultado = pstmt.executeUpdate();
        banco.fecharConexao();

        if (resultado == -1)
            return "Erro ao inserir registro";

        return "Registro inserido com sucesso";
    }

    public String atualizar(Integer id, String tipo, String nome, Integer frequencia_id, String data, Float valor )
    throws ClassNotFoundException, SQLException, ParseException{
        this.iniciaBanco();
        pstmt = banco.getConn().prepareStatement("update categorias set tipo =? , nome =? , " +
                " frequencia_id =? ,  data_agendada =? , valor =? where id =?");

        long resultado;

        pstmt.setString(1, tipo);
        pstmt.setString(2, nome);
        pstmt.setString(3, frequencia_id.toString());
        
        Date dt = sf1.parse(data);
        String nova = sf2.format(dt);
        pstmt.setString(4,nova);
        
        pstmt.setString(5, valor.toString());
        pstmt.setString(6, id.toString());

        resultado = pstmt.executeUpdate();
        banco.fecharConexao();

        if (resultado == -1)
            return "Erro ao atualizar registro";

        return "Registro atualizado com sucesso";
    }

    public String deletar(Integer id) throws SQLException, ClassNotFoundException{
        this.iniciaBanco();
        long resultado;
        resultado =stmt.executeUpdate("delete from categorias where id = "+id);
        banco.fecharConexao();

        if (resultado == -1)
            return "Erro ao deletar registro";

        return "Registro deletado com sucesso";
    }

    public boolean temMovimento(Integer id) throws SQLException, ClassNotFoundException{
        this.iniciaBanco();
        boolean tem;
        String query = "select categorias.* from categorias inner join movimentos on categorias.id = categoria_id where " +
                "categorias.id = "+id +"  limit 1 ";

        String descricao ="";

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()){
            descricao = rs.getString("nome");
        }

        banco.fecharConexao();


        if (descricao.equals(""))
            tem = false;
        else
            tem = true;

        return tem;
    }

    public ArrayList<Categoria> listaTodasCategorias() throws SQLException, ClassNotFoundException, ParseException{
        this.iniciaBanco();
        String query = "select * from categorias order by nome";

        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Categoria> array = new ArrayList<Categoria>();

        while (rs.next()){
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            
            Date dt = sf2.parse(rs.getDate("data_agendada").toString());
            String nova = sf1.format(dt);
            
            categoria.setDataAgendada(nova);
            categoria.setFrequencia_id(rs.getInt("frequencia_id"));
            categoria.setTipo(rs.getString("tipo"));
            categoria.setValor(rs.getFloat("valor"));

            array.add(categoria);
        }

        banco.fecharConexao();

        return array;
    }

    public Categoria buscaPorNome(String nome) throws SQLException, ClassNotFoundException, ParseException{
        this.iniciaBanco();
        String query = "SELECT * FROM categorias where nome like '"+nome+"'";

        ResultSet rs = stmt.executeQuery(query);

        Categoria categoria_model = new Categoria();

        while (rs.next()) {
            categoria_model.setId(rs.getInt("id"));
            categoria_model.setNome(rs.getString("nome"));
            Date dt = sf2.parse(rs.getDate("data_agendada").toString());
            String nova = sf1.format(dt);
            
            categoria_model.setDataAgendada(nova);
            categoria_model.setFrequencia_id(rs.getInt("frequencia_id"));
            categoria_model.setTipo(rs.getString("tipo"));
            categoria_model.setValor(rs.getFloat("valor"));

        }
        banco.fecharConexao();

        return categoria_model;
    }
    // categoria com nome já existente
    public Boolean validaNomeCategoria(String nome, Integer id) throws SQLException, ClassNotFoundException, ParseException{
        
        Boolean valida;
        Categoria categoria = this.buscaPorNome(nome);

        if (categoria.getNome() == null)
            categoria.setNome("");

        //alteração
        if (id !=0) {
            if (categoria.getNome().equals(""))
                valida = true;
            else if ((categoria.getNome().trim().equals(nome)) && (categoria.getId().toString().equals(id.toString())))
                valida = true;
            else
                valida = false;
        }else {
            if (categoria.getNome().trim().equals(nome) )
                valida = false;
            else
                valida = true;
        }
        return valida;
    }

    public ArrayList<Categoria> buscaCategoriasEventuais() throws SQLException, ClassNotFoundException, ParseException{
        this.iniciaBanco();
        
        String query = "select categorias.* from categorias left join movimentos on"
                + " categorias.id = categoria_id where categoria_id is null "+
        "and data_agendada <= date(now()) and frequencia_id = 1";

        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Categoria> array = new ArrayList<Categoria>();

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            Date dt = sf2.parse(rs.getDate("data_agendada").toString());
            String nova = sf1.format(dt);
            
            categoria.setDataAgendada(nova);
            categoria.setFrequencia_id(rs.getInt("frequencia_id"));
            categoria.setTipo(rs.getString("tipo"));
            categoria.setValor(rs.getFloat("valor"));

            array.add(categoria);
        }
        banco.fecharConexao();

        return array;
    }

    public ArrayList<Categoria> buscaCategoriasFrequentes() throws SQLException, ClassNotFoundException, ParseException {
       this.iniciaBanco();
        String query = "select categorias.* from categorias where frequencia_id <> 1 " +
                "and data_agendada <= date(now());";

        ResultSet rs = stmt.executeQuery(query);

        ArrayList<Categoria> array = new ArrayList<Categoria>();

        while (rs.next()) {

            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            Date dt = sf2.parse(rs.getDate("data_agendada").toString());
            String nova = sf1.format(dt);
            
            categoria.setDataAgendada(nova);
            categoria.setFrequencia_id(rs.getInt("frequencia_id"));
            categoria.setTipo(rs.getString("tipo"));
            categoria.setValor(rs.getFloat("valor"));

            array.add(categoria);
        }
        banco.fecharConexao();

        return array;
    }
   
}
