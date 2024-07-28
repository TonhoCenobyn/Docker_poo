<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="estilo.css" rel="stylesheet">
    <title>Pagina principal</title>
</head>

<body>
<div class="container">
    <h2>CADASTRAR-SE NO SISTEMA</h2>
    <form action="cadastroUsuario" method="post">
        <div class="mb-3">
            <label class="form-label">Nome:</label>
            <input type="text" placeholder="nome" name="nome" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Email:</label>
            <input type="email" placeholder="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Senha:</label>
            <input type="password" placeholder="senha" name="senha" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Telefone:</label>
            <input type="text" placeholder="telefone" name="telefone" class="form-control" required>
        </div>
        <div class = "mb-3">
            <input type="hidden" name="permissao" value="0">
            <!--
            <label class="form-label">Permissão do usuário:</label>
            <select name="permissao" class="form-select">
                <option value="0" selected>Cliente</option>
                <option value="1">Admin</option>
            </select>-->
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar-se</button>
    </form>
    <p>Já possui cadastro? então <a href="index.jsp">logue aqui</a></p>
    <c:if test="${not empty erroCadastro}">
        <h2 style="color: red">${erroCadastro} </h2>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
