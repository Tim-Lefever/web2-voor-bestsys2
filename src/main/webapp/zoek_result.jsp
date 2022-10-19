<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Puzzel" %>
<%@ page import="domain.db.PuzzelDB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
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
    <article>
        <h2>Zoek</h2>
        <p>U hebt gezocht naar ${zoekOpdracht} en wij vonden ${filteredPuzzels.size()}
            overeenkomst${filteredPuzzels.size() == 1 ? "" : "en"}!</p>
        <c:if test="${filteredPuzzels.size() != 0}">
            <table class="overview">
                <tr>
                    <th>Nr.</th>
                    <th>Naam</th>
                    <th>Merk</th>
                    <th>Aantal Stukken</th>
                    <th>Thema</th>
                    <th>Dominante Kleur</th>
                    <th>Pas aan</th>
                    <th>Verwijder</th>
                </tr>
                <c:forEach var="puzzel" items="${filteredPuzzels}" varStatus="ctr">
                    <tr>
                        <td>${ctr.index + 1}
                        </td>
                        <td>${puzzel.naam}
                        </td>
                        <td>${puzzel.merk}
                        </td>
                        <td>${puzzel.aantalStukken}
                        </td>
                        <td>${puzzel.thema}
                        </td>
                        <td>${puzzel.kleur}
                        </td>
                        <td><a href="Controller?command=pasAan&id=${puzzel.id}">
                            <img class="editImage" src="img/edit.jpg" alt="Pas aan">
                        </a></td>
                        <td><a href="Controller?command=verwijderConfirmation&id=${puzzel.id}">
                            <img class="deleteImage" src="img/removeIcon.png" alt="Verwijder">
                        </a></td>

                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </article>
</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>
</body>
</html>
