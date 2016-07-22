<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
                <table>
                    <tr>
                        <td>Nom du projet : </td>
                        <td><sf:input path="Name"/></td>
                    </tr>
                    <tr>
                        <td>Date de fin : </td>
                        <td><sf:input path="endDate"/></td>
                    </tr>
                    <tr>
                        <td>Description : </td>
                        <td><sf:input path="description"/></td>
                    </tr>
                    <td><input type="submit" value="Validate Project"/></td>
                </table>
        </sf:form>
    </div>
</body>
</html>
