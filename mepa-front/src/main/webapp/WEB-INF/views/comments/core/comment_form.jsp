<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <%--<c:url var="addCustomCommnentsModelFormActionUrl" value="/comment/submit"></c:url>--%>
    <form:form role="form" action="/comment/submit" method="post">
        <div class="form-group">
            <label for="data">Leave a comment below:</label>
            <br/>
            <input id="data" name="userText" type="text" placeholder="Leave a comment"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>

    <div class="container">
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
    </div>
