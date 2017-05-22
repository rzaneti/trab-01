/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes_DAO;

import Exercicio_Pedido.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RafaelaEmília
 */
public class PedidoDAO {
    
    private PreparedStatement opNovo;
    private PreparedStatement opListar;
    private final PreparedStatement opBuscaPorItemPedido;
    private final PreparedStatement opListarDono;
    
    
    public PedidoDAO() throws Exception{
        
        Connection conexao = ConnectionFactory.createConnection();
        
        opNovo = conexao.prepareStatement("INSERT INTO pedido (nome, dono, pedido, valor) VALUES(?, ?, ?, ?)");
        
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        
         opBuscaPorItemPedido = conexao.prepareStatement("SELECT * FROM Item WHERE pedido = ?");
         
          opListarDono = conexao.prepareStatement("SELECT dono, SUM(valor) as valorTotal FROM Item GROUP BY dono");
    
        
    }    
    

    public void cria(Pedido novoPedido) throws Exception{
        
        try {
            opNovo.clearParameters();
            opNovo.setString(1, novoPedido.getNome());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setLong(3, novoPedido.getPedido());
            opNovo.setDouble(4, novoPedido.getValor());
            opNovo.executeUpdate();           
        
        } catch (SQLException ex){
            throw new Exception("Erro ao inserir contato");
        }
    }

    public List<Pedido> listAll() throws Exception {
        
        try{
            List<Pedido> pedidos = new ArrayList<>();
            
            ResultSet resultado = opListar.executeQuery();
            
            while (resultado.next()){
                Pedido novoPedido = new Pedido();
                novoPedido.setId(resultado.getLong("id"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getDouble("valor"));
                novoPedido.setPedido(resultado.getLong("pedido"));
                novoPedido.setAtualizacao(resultado.getDate("atualizacao"));
                
                pedidos.add(novoPedido);
                
            }
            return pedidos;
        }catch (SQLException ex){
            throw new Exception("Erro ao listar contatos no banco!", ex);
            
        
        }
    }

    public List<Pedido> listByPedido() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();

            while (resultado.next()) {
                Pedido novoItem = new Pedido();

                novoItem.setPedido(resultado.getLong("pedido"));
                novoItem.setValor(resultado.getFloat("valorTotal"));
                pedidos.add(novoItem);
            }

            return pedidos;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    }

     public List<Pedido> listByItensPedido(Integer pedido) throws Exception {
        try {
            List<Pedido> itenspedido = new ArrayList<>();
            
            
            opBuscaPorItemPedido.setInt(1, pedido);
            ResultSet resultado = opBuscaPorItemPedido.executeQuery();

            while (resultado.next()) {
                Pedido itemPedido = new Pedido();

                itemPedido.setId(resultado.getLong("id"));
                itemPedido.setPedido(resultado.getLong("pedido"));
                itemPedido.setDono(resultado.getString("dono"));
                itemPedido.setValor(resultado.getFloat("valor"));
                itemPedido.setNome(resultado.getString("nome"));
                itemPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                itenspedido.add(itemPedido);
            }

            return itenspedido;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    } 

    public List<Pedido> listByDono() throws Exception {
        try {
            List<Pedido> pedidosDono = new ArrayList<>();

            ResultSet resultado = opListarDono.executeQuery();

            while (resultado.next()) {
                Pedido novoItem = new Pedido();

                novoItem.setDono(resultado.getString("dono"));
                novoItem.setValor(resultado.getFloat("valorTotal"));
                pedidosDono.add(novoItem);
            }

            return pedidosDono;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    
}
}
