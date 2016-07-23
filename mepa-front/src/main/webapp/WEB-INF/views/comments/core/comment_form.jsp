<%@ include file="/WEB-INF/views/includes/common.jsp" %>


<form action="/authentification/signin/">
   <text>               </text> <input type="submit" value="Se Connecter">
</form>

<div class="container">
    <h2>Commentaires</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Crée par</th>
                <th>Il y a </th>
                <th>Commentaire</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${new_c_models}" var="c_model" varStatus="loop">
                <c:if test="${c_model.projectId == project.id}">
                    <tr>
                        <td>Quelqu'un</td>
                        <td><fmt:formatDate value="${c_model.created}" pattern="HH:mm:ss"/></td>
                        <td>${c_model.data}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form:form role="form" action="/comment/submit/${project.id}" method="post">
        <div class="form-group">
            <label for="data">Laisser un commentaire:</label>
            <br/>
            <textarea id="data" name="userText" type="text" rows="6" cols="50" >Commentez ici...</textarea>

        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</div>
