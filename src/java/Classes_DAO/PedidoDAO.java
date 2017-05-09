/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes_DAO;

import Exercicio_Pedido.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author RafaelaEmília
 */
public class PedidoDAO {
    
    private PreparedStatement opNovo;
    
    
    public PedidoDAO() throws Exception{
        
        Connection conexao = ConnectionFactory.createConnection();
        
        opNovo = conexao.prepareStatement("INSERT INTO pedido (nome, dono, pedido, valor) VALUES(?, ?,?)");
    
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
    
}
