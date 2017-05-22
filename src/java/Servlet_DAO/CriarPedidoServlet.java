/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet_DAO;

import Classes_DAO.PedidoDAO;
import Exercicio_Pedido.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RafaelaEmília
 */
@WebServlet(name = "CriarPedidoServlet", urlPatterns = {"/novo.html"})
public class CriarPedidoServlet extends HttpServlet {

    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pedido novoPedido = new Pedido();
        novoPedido.setNome(request.getParameter("nome"));
        novoPedido.setDono(request.getParameter("dono"));
        novoPedido.setPedido(Long.parseLong(request.getParameter("pedido")));
        novoPedido.setValor(Double.parseDouble(request.getParameter("valor")));
        
        try{
            PedidoDAO dao = new PedidoDAO();
            dao.cria(novoPedido);
        }catch (Exception ex){
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);
            return;            
        
        }
        
        response.sendRedirect("pedidos.html");
        
    }

 
}
