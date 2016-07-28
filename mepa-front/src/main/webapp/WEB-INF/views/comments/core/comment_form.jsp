<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<c:if test="${userco == null}">
    <form action="/authentification/signin/">
        <text></text>
        <input type="submit" value="Se Connecter">
    </form>
</c:if>
<div class="row">
        <div class="col-md-12">
            <h2>Commentaires</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                <tr>
                        <th>Crée par</th>
                        <th>A</th>
                        <th>Commentaires</th>
                    </tr>
                </thead>
                    <tbody>
                <c:forEach items="${new_c_models}" var="c_model" varStatus="loop">
                        <c:if test="${c_model.projectId == project.id}">
                            <tr>
                                <td>${c_model.user}</td>
                                <td><fmt:formatDate value="${c_model.created}" pattern="HH:mm:ss"/></td>
                                <td>${c_model.data}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
                </table>
    </div>
    <c:if test="${userco != null}">
            <form:form role="form" action="/comment/submit/${project.id}" method="post">
                <div class="form-group">
                    <label for="data">Laisser un commentaire :</label>
                    <br/>
                    <textarea id="data" name="userText" type="text" rows="6" cols="50"></textarea>
                </div>
                <button type="submit" class="btn btn-default">Commenter</button>
            </form:form>
    </c:if>

</div>
</div>