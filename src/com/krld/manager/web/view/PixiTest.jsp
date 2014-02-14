<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:genericpage>
    <jsp:attribute name="title">Pixi.js Test</jsp:attribute>
    <jsp:attribute name="head"><script src="js/pixi.dev.js"></script></jsp:attribute>
    <jsp:body><script src="js/pixiTest.js"></script>
    </jsp:body>
</t:genericpage>