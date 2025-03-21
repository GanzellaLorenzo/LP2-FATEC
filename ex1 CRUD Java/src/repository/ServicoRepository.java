package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Servico;

public class ServicoRepository extends Conexao implements CRUD<Servico> {

    public ServicoRepository() throws Exception {}

    @Override
    public boolean incluir(Servico servico) throws Exception {
        String sql = "INSERT INTO tbServicos (descricao, valor) VALUES (?, ?)";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, servico.getDescricao());
        ps.setFloat(2, servico.getValor());
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean editar(Servico servico) throws Exception {
        String sql = "UPDATE tbServicos SET descricao = ?, valor = ? WHERE id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, servico.getDescricao());
        ps.setFloat(2, servico.getValor());
        ps.setInt(3, servico.getId());
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM tbServicos WHERE id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public Servico obter(int id) throws Exception {
        String sql = "SELECT * FROM tbServicos WHERE id = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet dados = ps.executeQuery();
        if (dados.next()) {
            return new Servico(
                dados.getInt("id"),
                dados.getString("descricao"),
                dados.getFloat("valor")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Servico> listar() throws Exception {
        String sql = "SELECT * FROM tbServicos ORDER BY descricao"; 
        ArrayList<Servico> lista = new ArrayList<>();
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ResultSet dados = ps.executeQuery();
        while (dados.next()) {
            lista.add(new Servico(
                dados.getInt("id"),
                dados.getString("descricao"),
                dados.getFloat("valor")
            ));
        }
        return lista;
    }

    @Override
    public ArrayList<Servico> pesquisar(String pesquisa) throws Exception {
        String sql = "SELECT * FROM tbServicos WHERE descricao LIKE ?";
        ArrayList<Servico> lista = new ArrayList<>();
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, "%" + pesquisa + "%");
        ResultSet dados = ps.executeQuery();
        while (dados.next()) {
            lista.add(new Servico(
                dados.getInt("id"),
                dados.getString("descricao"),
                dados.getFloat("valor")
            ));
        }
        return lista;
    }
}