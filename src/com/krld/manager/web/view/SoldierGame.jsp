<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:genericpage>
    <jsp:attribute name="title">У солдата выходной</jsp:attribute>
    <jsp:attribute name="head"><script src="js/pixi.dev.js"></script></jsp:attribute>
    <jsp:body>
        <div id="gameview"></div>
        <div id="bulletsAndGun" style="background-color: lightcyan"></div>
        <div id="listGuns"></div>
        <div id="statsFooter" style="margin-top: 10px;"><ul><li>Name - Kills - Deaths </li></ul></div>
        <div id="stats" style=" color: lightseagreen"></div>
        <script src="game/js/SoldierGame.js">
        </script>
    </jsp:body>
</t:genericpage>