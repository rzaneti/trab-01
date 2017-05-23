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
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorItens;
    
    public PedidoDAO() throws Exception{
        
        Connection conexao = ConnectionFactory.createConnection();
        
        opNovo = conexao.prepareStatement("INSERT INTO pedido (nome, dono, pedido, valor) VALUES(?, ?, ?, ?)");
        
        opListar = conexao.prepareStatement("SELECT * FROM pedido");
        
        opBuscaPorItemPedido = conexao.prepareStatement("SELECT * FROM Item WHERE pedido = ?");
         
        opListarDono = conexao.prepareStatement("SELECT dono, SUM(valor) as valorTotal FROM Item GROUP BY dono");
        
        opAtualiza = conexao.prepareStatement("UPDATE Item SET Pedido = ?, Dono = ?, Valor=?, Nome=? WHERE id = ?");
        
        opBuscaPorItens = conexao.prepareStatement("SELECT * FROM Item WHERE id = ?");
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
                itemPedido.setAtualizacao(resultado.getDate("atualizacao"));
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

     public void atualiza(Pedido pedido) throws Exception {
        try {
            opAtualiza.clearParameters();
            opAtualiza.setLong(1, pedido.getPedido());
            opAtualiza.setString(2, pedido.getDono());
            opAtualiza.setFloat(3, (float) pedido.getValor());
            opAtualiza.setString(4, pedido.getNome());
            opAtualiza.setLong(5, pedido.getId());
            opAtualiza.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro atualizar Item!", ex);
        }
    }

     public Pedido getByPedido(Long id) throws Exception {
        try {
            Pedido pedido = null;
            opBuscaPorItens.clearParameters();
            opBuscaPorItens.setLong(1, id);

            ResultSet resultado = opBuscaPorItens.executeQuery();

            if (resultado.next()) {
                pedido = new Pedido();

                pedido.setId(resultado.getLong("id"));
                pedido.setPedido(resultado.getLong("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getFloat("valor"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setAtualizacao(resultado.getDate("atualizacao"));
            }
            return pedido;

        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar os pedidos no banco!", ex);
        }
    }
}
