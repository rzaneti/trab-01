/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet_DAO;

import Classes_DAO.PedidoDAO;
import Exercicio_Pedido.Pedido;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RafaelaEmília
 */
@WebServlet(name = "ListaPedidosServlet", urlPatterns = {"/pedidos.html"})
public class ListaPedidosServlet extends HttpServlet {

    
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/listaPedido.html")) {
            List<Pedido> pedidos;

            try {
                PedidoDAO dao = new PedidoDAO();
                pedidos = dao.listByPedido();

            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-Pedidos.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listarItemPedido.html")) {

            List<Pedido> Itenspedido;

            try {
                PedidoDAO dao = new PedidoDAO();
                int id = Integer.parseInt(request.getParameter("pedido"));
                Itenspedido = dao.listByItensPedido(id);
            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                Itenspedido = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("Itenspedido", Itenspedido);
            request.getRequestDispatcher("WEB-INF/lista-itemPedido.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listaDono.html")) {
            List<Pedido> pedidos;

            try {
                PedidoDAO dao = new PedidoDAO();
                pedidos = dao.listByDono();

            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-porDono.jsp").forward(request, response);
        }
    }
   
}
