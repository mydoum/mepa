<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <c:if test="${noUsers == true}">
        <div class="col-md-12 text-center alert alert-danger col-sm-12">
            Erreur. Il n'y a aucun utilisateur à exporter.
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-12 investFormInside">
            <tr/>
            <div class="col-md-12 investFormInside">
                <div class="col-md-2">
                    <div class="col-md-2"></div>
                    <div class="col-md-2"></div>
                </div>
                <div class="col-md-8" style="text-align: center"><h2 >Liste des Utilisateurs</h2></div>
                <div class="col-md-3">
                    <div class="col-md-3"></div>
                    <div class="col-md-3"></div>
                </div>
            </div>
            <br/>
            <table class="col-md-12 table table-striped">
                <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${usersList.size() > 0}">
                    <c:forEach items="${usersList}" var="appUser" varStatus="status">
                        <tr>
                            <td>${appUser.firstName}</td>
                            <td>${appUser.lastName}</td>
                            <td>${appUser.login}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <br/>
        <div class="col-md-12 investFormInside">
            <c:url var="exportActionUrl" value="/authentification/exportUsers"/>
            <form id="eventForm" class="form-horizontal" action="${exportActionUrl}"
                  method="POST">
                <fieldset>
                    <div class="control-group">
                        <!-- Buttons -->
                        <div class="controls">
                            <button type="submit" class="btn btn-success" style="display: block; margin: 0 auto;">
                                <span class="glyphicon glyphicon-export"></span>  Exporter</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <br/>
    </div>
</div>