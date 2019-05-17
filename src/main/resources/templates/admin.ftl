<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="submit" value="Sign Out"/>
</form>
<form method="post" action="/admin">
    <input type="number" name="id" placeholder="id" required>
    <input type="text" name="name" placeholder="Name" required>
    <input type="text" name="description" placeholder="Description" required>
    <input type="number" step="0.1" name="price" placeholder="Price" required>
    <input type="text" name="pic" placeholder="Url Of Picture" required>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit">Добавить</button>
</form>

<div>List of products</div>
<form method="get" action="/admin">
    <input type="text" name="filter" placeholder="Name of Product">
    <button type="submit">Найти</button>
</form>

<#list products as product>
    <div>
        <b>${product.id}</b>
        <form action="/admin/${product.id}" method="post">
            <input type="text" name="name" value="${product.name}">
            <input type="text" name="description" value="${product.description}">
            <input type="number" step="0.1" name="price" value="${product.price}">
            <input type="text" name="pic" value="${product.pic}">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Save</button>
        </form>
    </div>
</#list>
</body>
</html>