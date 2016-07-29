<%@ include file="/WEB-INF/views/includes/common.jsp" %>
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
                        <c:if test="${c_model.projectId_ == project.id}">
                            <tr>
                                <td>${c_model.user_}</td>
                                <td><fmt:formatDate value="${c_model.created_}" pattern="HH:mm:ss"/></td>
                                <td>${c_model.data_}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
                </table>
    </div>
            <c:choose>
                        <c:when test="${userCo == null}">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-10">
                                    <p align="center" class="glyphicon glyphicon-info-sign">
                                        Vous devez être identifié pour laisser un commentaire
                                    </p>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <p align="center">
                                        <a class="btn btn-success loginButton" href="/authentification/signin" role="button">Connexion</a>
                                    </p>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <p align="center">
                                        <a href="/authentification/signup">S'inscrire</a>
                                    </p>

                                </div>
                                <div class="col-md-4"></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form:form role="form" action="/comment/submit/${project.id}" method="post">
                                <div class="form-group">
                                    <label for="data">Laisser un commentaire :</label>
                                    <br/>
                                    <textarea id="data" name="userText" type="text" rows="6" cols="50"></textarea>
                                </div>
                                <button type="submit" class="btn btn-default">Commenter</button>
                            </form:form>
                        </c:otherwise>
                    </c:choose>
</div>
</div>