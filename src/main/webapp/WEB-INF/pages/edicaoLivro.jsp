<%@ page
        contentType="text/html;charset=UTF-8"
        language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="dao" class="dao.LivroDAO"/>
<jsp:useBean id="daoGenero" class="dao.GeneroDAO"/>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Pagina principal</title>
</head>

<body>
<c:if test="${sessionScope.get('usuario') == null}">
    <c:redirect url="../../index.jsp"/>
</c:if>
    <header style="background-color: #BDC1C9; color: white; text-align: center; padding: 3rem;">
        <h2 style="color: black">BookReader: Adquira seu livro aqui.</h2>
    </header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid" style="border-bottom: 2px solid black">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-book-half" viewBox="0 0 16 16">
                <path d="M8.5 2.687c.654-.689 1.782-.886 3.112-.752 1.234.124 2.503.523 3.388.893v9.923c-.918-.35-2.107-.692-3.287-.81-1.094-.111-2.278-.039-3.213.492zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783"/>
            </svg>
            <a class="navbar-brand" href="#">bookReader</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link disabled" aria-current="page" href="navServlet?opcao=home">Página principal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-disabled="true" href="navServlet?opcao=generos">Gêneros cadastrados</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-disabled="true" href="navServlet?opcao=cadastroAdm">Cadastrar Administrador</a>
                    </li>
                </ul>
                <div class="d-flex" style="display: flex; gap: 2rem;">
                    <div style="display: flex; flex-direction: column">
                        <p style="font-size: 24px;">Bem vindo, ${sessionScope.get("usuario").nome}</p>
                    </div>
                    <a href="navServlet?opcao=sair" style="text-decoration: none"><p style="font-size: 24px; color: red">Sair da conta</p></a>
                </div>
            </div>
        </div>
    </nav>
<div class="container">
    <h2>EDITAR LIVRO</h2>
    <form action="cadastroLivro" method="post">
        <div class="mb-3">
            <label class="form-label">Titulo do livro:</label>
            <input value="<c:out value="${dao.getLivro(id).nome}"/>" type="text" placeholder="titulo" name="titulo" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Autor do livro:</label>
            <input value="<c:out value="${dao.getLivro(id).autor}"/>" type="text" placeholder="autor" name="autor" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Preço do livro:</label>
            <input value="<c:out value="${dao.getLivro(id).preco}"/>" type="number" step="0.01" placeholder="preco" name="preco" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Genero:</label>
            <select name="genero" class="form-select">
                <option value="0" selected>Selecione um gênero</option>
                <c:forEach var = "genero" items="${daoGenero.generos}">
                    <option value="${genero.id}">${genero.nome}</option>
                </c:forEach>
            </select>
        </div>
        <input hidden="hidden" name="id" value="<c:out value="${id}"/>">
        <input hidden="hidden" name="opcao" value="editar">
        <button type="submit" class="btn btn-primary">Salvar alterações</button>
    </form>
    <a href="cadastroLivro?redirect=dashboard">Cancelar edição</a>
    <c:if test="${not empty erroCadEd}">
        <h2 style="color: red">${erroCadEd} </h2>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
