<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <title>Tim's puzzelbox</title>
    <link rel="icon" href="img/logo.jpg">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style/normalize.css">
    <link rel="stylesheet" href="style/stylesheet.css">
</head>
<body>
<header>

    <h1>Tim's puzzelbox</h1>
    <%@ include file="navigation.jspf" %>
</header>
<main>
    <article id="homeArt">
        <h2>Zoekpagina</h2>
        <p>Vind je een bepaalde puzzel niet meteen op de overzichtspagina?</p>
        <p>Zoek hier naar je puzzel.</p>
        <%--@elvariable id="foutmelding" type="string"--%>
        <c:if test="${foutmelding != null}">
            <ul class="foutmelding">
                <li>${foutmelding}</li>
            </ul>
        </c:if>
        <form action="Controller?command=zoek" class="form">
            <input type="hidden" name="command" value="zoek">
            <label for="zoekbalk">Naam:</label>
            <input type="text" id="zoekbalk" name="zoekOpdracht" placeholder="Bv.: Katten">
            <input type="submit" value="zoek" class="submit">
        </form>

    </article>
</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>
</body>
</html>
