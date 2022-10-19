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
    <article id="verwijderPuzzel">
        <h2>Verwijderen</h2>
        <p>Je staat op het punt volgende puzzel definitief te verwijderen, weet je dit zeker?</p>
        <table class="overview">
            <tr>
                <th>Naam</th>
                <th>Merk</th>
                <th>Aantal Stukken</th>
                <th>Thema</th>
                <th>Dominante Kleur</th>
            </tr>
            <tr>
                <%--@elvariable id="teVerwijderenPuzzel" type="domain.model.Puzzel"--%>
                <td>${teVerwijderenPuzzel.naam}
                </td>
                <td>${teVerwijderenPuzzel.merk}
                </td>
                <td>${teVerwijderenPuzzel.aantalStukken}
                </td>
                <td>${teVerwijderenPuzzel.thema}
                </td>
                <td>${teVerwijderenPuzzel.kleur}
                </td>
        </table>
        <ul class="knoppenlijst">
            <li>
                <form method="post" action="Controller">
                    <input type="hidden" name="command" value="verwijder">
                    <input type="hidden" name="id" value="${teVerwijderenPuzzel.id}">
                    <button id="ja" class="green" type="submit">
                        Ja
                    </button>
                </form>
            </li>
            <li>
                <form method="get" action="Controller">
                    <input type="hidden" name="command" value="overview">
                    <button id="nee" class="red" type="submit">
                        Nee
                    </button>
                </form>
            </li>
        </ul>
    </article>
</main>
<footer>
    <p>Webontwikkeling 2, Tim Lefever</p>
</footer>

</body>
</html>
