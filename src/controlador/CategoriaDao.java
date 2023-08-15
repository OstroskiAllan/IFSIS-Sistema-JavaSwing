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
import modelo.Categoria;

/**
 *
 * @author Ostroski
 */
public class CategoriaDao {
    public int inserir (Categoria c) throws Exception {
        int retorno;
       
        String sql = "insert into categoria (nome)"
                + "values (?)";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, c.getNome());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }
    
    
    
    public List<Categoria>buscar(String nome)throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from categoria";
        
        if(!nome.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Categoria> lista = new ArrayList<>();
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Categoria c = new Categoria();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    
                    
                    lista.add(c);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        return lista;
    }
    public Categoria getCategoria(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from categoria where id = ?";
        
        Categoria obj = null;
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //Parâmetros da SQL (pode ser mais de 1 param)
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Categoria();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        
        return obj;
    }

    public int atualizar(Categoria c) throws Exception {
        int retorno;

        String sql = "update categoria"
                + "      set nome  = ?"
                + "    where id    = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setInt(2, c.getId());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }

    public void excluir(Integer id) throws Exception{
        String sql = "delete from categoria where id = ?";
        
        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }
        
    }

}
