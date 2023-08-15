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
import modelo.Produto;

/**
 *3. Entidade: produto
    id int PK AI
    nome varchar(100) not null
    unidadeDeMedida varchar(15)
    datacadastro datetime default current_timestamp

 * @author Ostroski
 */
public class ProdutoDao {
    public int inserir(Produto p) throws Exception {
        int retorno;

        String sql = "insert into produto (nome, unidadeDeMedida)"
                + "values (?, ?)";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getUnidadeDeMedida());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }
    
    
    
    public List<Produto>buscar(String nome)throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from produto";
        
        if(!nome.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Produto> lista = new ArrayList<>();
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setUnidadeDeMedida(rs.getString("unidadeDeMedida"));
                    p.setDataCadastro(rs.getTimestamp("datacadastro"));
                    
                    lista.add(p);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        return lista;
    }
    public Produto getProduto(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from produto where id = ?";
        
        Produto obj = null;
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //Parâmetros da SQL (pode ser mais de 1 param)
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Produto();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                    obj.setUnidadeDeMedida(rs.getString("unidadeDeMedida"));
               
                }
            }
        } catch (Exception e) {
            throw e;
        }
        
        return obj;
    }

    public int atualizar(Produto u) throws Exception {
        int retorno;

        String sql = "update produto"
                + "      set nome  = ?,"
                + "  unidadeDeMedida = ?"
                + "    where id    = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getUnidadeDeMedida());
            ps.setInt(3, u.getId());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }

    public void excluir(Integer id) throws Exception{
        String sql = "delete from produto where id = ?";
        
        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }
        
    }

}

