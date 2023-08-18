/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDao {
    
     public int inserir(Cliente c) throws Exception {
        int retorno;

        String sql = "insert into cliente (nome, tipoCliente, cpfCnpj, telefone, email, observacao)"
                + "values (?, ?, ?, ?, ?, ?)";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
             ps.setString(1, c.getNome());
            ps.setInt(2, c.getTipoCliente());
            ps.setString(3, c.getCpfCnpj());
            ps.setString(4, c.getTelefone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getObservacao());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }
    
    
    
    public List<Cliente>buscar(String nome)throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from cliente";
        
        if(!nome.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Cliente> lista = new ArrayList<>();
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setTipoCliente(rs.getInt("tipoCliente"));
                    c.setCpfCnpj(rs.getString("cpfCnpj"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    c.setObservacao(rs.getString("observacao"));
                    c.setDataCadastro(rs.getTimestamp("dataCadastro"));
                    
                    
                    lista.add(c);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        return lista;
    }
    public Cliente getCliente(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from cliente where id = ?";
        
        Cliente obj = null;
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //Parâmetros da SQL (pode ser mais de 1 param)
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Cliente();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setTipoCliente(rs.getInt("tipoCliente"));
                    obj.setCpfCnpj(rs.getString("cpfCnpj"));
                    obj.setTelefone(rs.getString("telefone"));
                    obj.setEmail(rs.getString("email"));
                    obj.setObservacao(rs.getString("observacao"));
                    obj.setDataCadastro(rs.getDate("dataCadastro"));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        
        return obj;
    }

    public int atualizar(Cliente c) throws Exception {
        int retorno;

        String sql = "update cliente"
                 + " SET nome = ?,"
                + " tipoCliente = ?,"
                + " cpfCnpj = ?,"
                + " telefone = ?,"
                + " email = ?,"
                + " observacao = ?"
                + " WHERE id = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setInt(2, c.getTipoCliente());
            ps.setString(3, c.getCpfCnpj());
            ps.setString(4, c.getTelefone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getObservacao());
            ps.setInt(7, c.getId());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }

    public void excluir(Integer id) throws Exception{
        String sql = "delete from cliente where id = ?";
        
        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }
        
    }

}
