<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.db.PuzzelDB" %>
<%@ page import="domain.model.Puzzel" %>
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
    <%@ include file="navigation.jspf"%>
</header>
<main>
    <article>
        <h2>Pas aan</h2>
        <p>Hier kan je de puzzel aanpassen!</p>
        <%--@elvariable id="errors" type="java.util.ArrayList<>"--%>
        <c:if test="${not empty errors}">
            <ul class="foutmelding">
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        <form class="form" method="post" action="Controller?command=pasAanConfirmation" novalidate>
            <label for="Naam">Naam:</label>
            <input type="text" id="Naam" name="naam" value="${aanTePassenPuzzel.naam}" class="${naamHasErrors ? "foutiefVeld" : ""}" required>
            <label for="Merk">Merk:</label>
            <input type="text" id="Merk" name="merk" value="${aanTePassenPuzzel.merk}" class="${merkHasErrors ? "foutiefVeld" : ""}" required>
            <label for="Stukken">Aantal Stukken:</label>
            <input type="number" min="0" id="Stukken" name="aantalStukken" value="${aanTePassenPuzzel.aantalStukken}" class="${aantalStukkenHasErrors ? "foutiefVeld" : ""}" required>
            <label for="Thema">Thema:</label>
            <input type="text" id="Thema" name="thema" value="${aanTePassenPuzzel.thema}" class="${themaHasErrors ? "foutiefVeld" : ""}" required>
            <label for="Kleur">Dominante kleur:</label>
            <input type="text" id="Kleur" name="kleur" value="${aanTePassenPuzzel.kleur}" class="${kleurHasErrors ? "foutiefVeld" : ""}" required>
            <input type="submit" value="Verstuur" id="sendPuzzle" class="submit">

            <input type="hidden" name="command" value="pasAanConfirmation">
            <input type="hidden" name="id" value="${aanTePassenPuzzel.id}">
        </form>
    </article>

</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>
</body>
</html>
