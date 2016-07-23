<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h2>Other Commnents</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Created By</th>
                <th>Created At</th>
                <th>Comment</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${new_c_models}" var="c_model" varStatus="loop">
                <c:if test="${c_model.projectId == project.id}">
                    <tr>
                        <td>Someone</td>
                        <td><fmt:formatDate value="${c_model.created}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td>${c_model.data}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form:form role="form" action="/comment/submit/${project.id}" method="post">
        <div class="form-group">
            <label for="data">Leave a comment below:</label>
            <br/>
            <textarea id="data" name="userText" type="text" rows="6" cols="50" >Please comment...</textarea>

        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>
