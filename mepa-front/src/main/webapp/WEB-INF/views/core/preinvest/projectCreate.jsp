<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 21/07/2016
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<html>
<head>
    <title>Create a new project</title>
</head>
<body>
    <div class="container">
        <c:if test="${is_connected}">
            <sf:form method="post" modelAttribute="newProject" action="processCreation">
                <div class="control-group">
                    <label class="control-label">Nom du projet</label>
                    <div class="controls">
                        <td><form:input path="Name" class="form-control input-lg" placeholder="Définissez le nom du projet"/></td>
                    </div>
                </div>
                <br/>
                <div class="control-group">
                    <label class="control-label">Date de début</label>
                    <div class="controls">
                        <td><form:input path="startDate" class="form-control input-lg" placeholder="mm/jj/aaaa"/></td>
                    </div>
                </div>
                <br/>
                <div class="control-group">
                    <label class="control-label">Date de fin</label>
                    <div class="controls">
                        <td><form:input path="endDate" class="form-control input-lg" placeholder="mm/jj/aaaa"/></td>
                    </div>
                </div>
                <br/>
                <div class="control-group">
                    <label class="control-label">Description</label>
                    <div class="controls">
                        <td><form:textarea path="description" class="form-control input-lg" placeholder="Description" style="margin-top: 0px;"/></td>
                    </div>
                </div>
                <br/>
                <div class="control-group">
                   <button type="submit" class="btn btn-default">Créer le projet</button>
                </div>
            </sf:form>

        </c:if>
        <c:if test="${!is_connected}">
            <h1> Vous devez être connecté pour ajouter un projet.</h1>
        </c:if>
    </div>
</body>
</html>
