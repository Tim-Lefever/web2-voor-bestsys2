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
        <h2>Weet u het zeker?</h2>
        <p>U staat op het punt een puzzel te veranderen. Weet u het zeker?</p>
        <table class="overview">
            <tr>
                <td></td>
                <th>Naam</th>
                <th>Thema</th>
                <th>Aantal Stukken</th>
                <th>Thema</th>
                <th>Dominante Kleur</th>
            </tr>
            <tr>
                <th>Oud</th>
                <%--@elvariable id="aanTePassenPuzzel" type="domain.model.Puzzel"--%>
                <td>${aanTePassenPuzzel.naam}</td>
                <td>${aanTePassenPuzzel.merk}</td>
                <td>${aanTePassenPuzzel.aantalStukken}</td>
                <td>${aanTePassenPuzzel.thema}</td>
                <td>${aanTePassenPuzzel.kleur}</td>
            </tr>
            <tr>
                <%--@elvariable id="newPuzzel" type="domain.model.Puzzel"--%>
                <th>Nieuw</th>
                <td>${newPuzzel.naam}</td>
                <td>${newPuzzel.merk}</td>
                <td>${newPuzzel.aantalStukken}</td>
                <td>${newPuzzel.thema}</td>
                <td>${newPuzzel.kleur}</td>
            </tr>
        </table>
        <ul class="knoppenlijst">
            <li class="green" ><form method="post" action="Controller">
                <input type="hidden" name="command" value="pasAanConfirmed">
                <button id="ja" class="green" type="submit">
                    Ja
                </button>
            </form></li>
            <li><form method="get" action="Controller">
                <input type="hidden" name="command" value="overview">
                <button id="nee" class="red" type="submit">
                    Nee
                </button>
            </form></li>
        </ul>
    </article>

</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>
</body>
</html>