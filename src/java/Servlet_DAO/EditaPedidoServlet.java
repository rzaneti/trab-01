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
@WebServlet(name = "EditaPedidoServlet", urlPatterns = {"/EditaPedidoServlet"})
public class EditaPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            PedidoDAO dao = new PedidoDAO();
            Pedido item = dao.getByPedido(id);

            request.setAttribute("item", item);

            request.getRequestDispatcher("WEB-INF/edita-pedido.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("listarItemPedido.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listarItemPedido.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));

            PedidoDAO dao = new PedidoDAO();
            Pedido pedido = dao.getByPedido(id);
            System.out.println(pedido.getDono());

            pedido.setPedido(Long.parseLong(request.getParameter("pedido")));
            pedido.setDono(request.getParameter("dono"));
            pedido.setValor(Float.parseFloat(request.getParameter("valor")));
            pedido.setNome(request.getParameter("nome"));

            dao.atualiza(pedido);
            response.sendRedirect("listarItemPedido.html?pedido=" + pedido.getPedido());

        } catch (NumberFormatException e) {
            response.sendRedirect("listarItemPedido.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listarItemPedido.html");
        }
    }
}
