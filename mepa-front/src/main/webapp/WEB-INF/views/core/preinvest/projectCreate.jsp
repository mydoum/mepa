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
        <sf:form method="post" modelAttribute="newProject" action="processCreation">
            <div class="input-group">
                <span class="input-group-addon" id="projectName">Nom du projet</span>
                <td><form:input path="Name" class="form-control" placeholder="Nom du projet" aria-describedby="basic-addon1"/></td>
            </div>
            <br/>
            <div class="input-group">
                <span class="input-group-addon" id="deadline">Date de fin</span>
                <td><form:input path="endDate" class="form-control" placeholder="Date de fin" aria-describedby="basic-addon1"/></td>
            </div>
            <br/>
            <div class="input-group">
                <span class="input-group-addon" id="description">Description</span>
                <td><form:input path="description" class="form-control" placeholder="Description" aria-describedby="basic-addon1"/></td>
            </div>
            <br/>
           <td>
               <span class="label label-primary"><input type="submit" value="Validate Project" style="background-color: transparent" style="border-color: transparent"/></span>
           </td>
        </sf:form>
    </div>
</body>
</html>
