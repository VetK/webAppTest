<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>List of products</div>

<form method="post" action="/list">
    <p><select name="sortByPrice">
            <option disabled>sort by</option>
            <option value="lowest">By lowest price</option>
            <option value="highest">By highest price</option>
        </select>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Send"></p>
</form>

<#list products as product>
<div>
    <b>${product.id}</b>
    <b>${product.name}</b>
    <b>${product.description}</b>
    <span>${product.price}</span>
    <div>
        <img src="${product.pic}" alt ="Not found">
    </div>
</div>
</#list>
</body>
</html>