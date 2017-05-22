<%-- 
    Document   : lista-pedidos
    Created on : 12/05/2017, 21:39:30
    Author     : RafaelaEmília
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="JSPF/Menu.jspf" %>
        <h1>Lista de Pedidos</h1>
        <div style="color: red;">${mensagem}</div>
        <table>
            <thead>
                <tr>
                <th>Pedido</th>
                <th>Valor Total</th>
                </tr>
            </thead>    
            <tbody>
                <c:forEach var="pedido" items="${pedidos}">    
                <tr>
                    <td><a href="listarItemPedido.html?pedido=${pedido.pedido}" value="${pedido.pedido}">${pedido.pedido}</a></td>
                    <td>${pedido.valor}</td>
                </tr>
                </c:forEach>
            </tbody>
    </body>
</html>
