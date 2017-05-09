<%-- 
    Document   : novo-pedido
    Created on : 08/05/2017, 22:00:51
    Author     : RafaelaEmília
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Pedido</title>
    </head>
    <body>
        <%@include file="JSPF/menu.jspf" %>
        <h1>Novo Pedido</h1>
        
        <form method="post">
            <div><label>Pedido: <input type="text" name="pedido" placeholder="digite o número do pedido"/></label></div>
            <div><label>Nome: <input type="text" name="nome" placeholder="digite o nome"/></label></div>
            <div><label>Dono: <input type="text" name="dono" placeholder="digite o dono"/></label></div>
            <div><label>Valor: <input type="text" name="valor" placeholder="digite o número valor"/></label></div>
            <div><input type="submit"/></div>
            
        </form>
        
    </body>
</html>
