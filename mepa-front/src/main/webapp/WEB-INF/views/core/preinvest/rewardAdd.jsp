<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: claebo_c
  Date: 26/07/16
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<html>
<head>
    <title>Add reward to project</title>
</head>
<body>
  <div class="container">
    <c:url var="addRewardUrl" value="/core/preinvest/rewardAdd/add/${newProject}"/>
    <a href="${addRewardUrl}"><button>Ajouter une contrepartie</button></a>
    <c:url var="listProjectUrl" value="/core/preinvest/rewardAdd/projectList"/>
    <button><a href="${listProjectUrl}">Valider le projet</a></button>
  </div>
</body>
</html>
