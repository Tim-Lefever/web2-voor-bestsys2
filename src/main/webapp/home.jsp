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
        <h2>Homepagina</h2>
        <div>
            <div>
                <p>Welkom op de home pagina van Tim's puzzelbox!</p>
                <p>De familie van Tim puzzelt veel in hun vrije tijd, en wilt daarom graag een overzicht van alle
                    puzzels die ze
                    momenteel in bezit hebben.</p>
                <p>Deze webpagina gebruiken ze om de puzzelcollectie bij te houden.</p>
                <%--@elvariable id="aantalPuzzels" type="int"--%>
                <p>Momenteel zijn er ${aantalPuzzels} puzzels opgeslagen.</p>
            </div>
            <img src="img/puzzel.jpg" alt="Puzzel" id="homePhoto">
        </div>
        <%--@elvariable id="idLaatstAangepast" type="int"--%>
        <c:if test="${idLaatstAangepast != null}">
            <p>Jij hebt het laatst de puzzel met id ${idLaatstAangepast} aangepast.</p>
            <c:choose>
                <c:when test="${laatsAangepastePuzzel != null}">
                    <table class="overview">
                        <tr>
                            <th>Naam</th>
                            <th>Merk</th>
                            <th>Aantal Stukken</th>
                            <th>Thema</th>
                            <th>Dominante Kleur</th>
                        </tr>
                        <tr>
                            <td>${laatsAangepastePuzzel.naam}
                            </td>
                            <td>${laatsAangepastePuzzel.merk}
                            </td>
                            <td>${laatsAangepastePuzzel.aantalStukken}
                            </td>
                            <td>${laatsAangepastePuzzel.thema}
                            </td>
                            <td>${laatsAangepastePuzzel.kleur}
                            </td>
                        </tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>Deze puzzel bestaat niet meer.</p>
                </c:otherwise>
            </c:choose>
        </c:if>
    </article>
</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>

</body>
</html>
