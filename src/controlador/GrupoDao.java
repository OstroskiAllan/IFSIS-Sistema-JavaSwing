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
import modelo.Grupo;

/**
 * @author Ostroski
 */
public class GrupoDao {
    public int inserir (Grupo g) throws Exception {
        int retorno;
       
        String sql = "insert into grupo (nome)"
                + "values (?)";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, g.getNome());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }
    
    
    
    public List<Grupo>buscar(String nome)throws Exception{
        Connection conexao = Conexao.getConexao();
        String sql = "select * from grupo";
        
        if(!nome.equals("")){
            sql += " where nome like ?";
        }
        
        sql += " order by nome";
        
        List<Grupo> lista = new ArrayList<>();
        
        try(PreparedStatement ps = conexao.prepareStatement(sql)){
            if(!nome.equals("")){
                ps.setString(1, "%" + nome + "%");
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Grupo g = new Grupo();
                    g.setId(rs.getInt("id"));
                    g.setNome(rs.getString("nome"));
                    
                    
                    lista.add(g);
                }
            }
            
        }catch (Exception e){
            throw e;
        }
        
        return lista;
    }
    public Grupo getGrupo(int id) throws Exception {
        Connection conexao = Conexao.getConexao();
        String sql = "select * from grupo where id = ?";
        
        Grupo obj = null;
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            //Parâmetros da SQL (pode ser mais de 1 param)
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    obj = new Grupo();
                    obj.setId(rs.getInt("id"));
                    obj.setNome(rs.getString("nome"));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        
        return obj;
    }

    public int atualizar(Grupo g) throws Exception {
        int retorno;

        String sql = "update grupo"
                + "      set nome  = ?"
                + "    where id    = ?";

        Connection conexao = Conexao.getConexao();
        try ( PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, g.getNome());
            ps.setInt(2, g.getId());

            retorno = ps.executeUpdate();
        }

        return retorno;
    }

    public void excluir(Integer id) throws Exception{
        String sql = "delete from grupo where id = ?";
        
        Connection conexao = Conexao.getConexao();
        try (PreparedStatement ps = conexao.prepareStatement(sql)){
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }
        
    }

}