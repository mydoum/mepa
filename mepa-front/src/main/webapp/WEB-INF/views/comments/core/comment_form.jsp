<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: prosp_000
  Date: 20/07/2016
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Commentary</title>
</head>
<body>
    <%--<label> Leave a comment below: </label>
    <form>
        <textarea name="nom" rows=4 cols=40> </textarea>
    </form>
    <button id="btn1" type="submit" class="btn btn-default">
    Submit your comment...
    </button>--%>

    <div class="container">
        <c:url var="addCustomCommnentsModelFormActionUrl" value="/commnents/core/add"></c:url>
        <form:form role="comments_form" action="${addCustomCommentsModelFormActionUrl}" modelAttribute="addCustomCommentsModelFormBean"
                   method="post">
            <div class="form-group">
                <label for="data">Leave a comment below:</label>
                <br/>
                <form:input id="data" path="data" type="text" placeholder="Leave a comment"/>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form:form>
    </div>

    <%--<div class="container">
        <h2>Other Commnents</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Created</th>
                    <th>Data</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${commentsmodels}" var="commentsmodel" varStatus="loop">
                    <tr>
                        <td><fmt:formatDate value="${commentsodel.created}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td>${commentsmodel.data}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>--%>
</body>
</html>
