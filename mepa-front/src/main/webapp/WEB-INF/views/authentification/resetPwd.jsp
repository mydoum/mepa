<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 29/7/2016
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <c:choose>
        <c:when test="${isNotSent == false}">
            <div class="col-md-12 text-center alert alert-success investFormInside">
                Un mail a été envoyé à ${email}!
            </div>
            <br/>
        </c:when>
        <c:when test="${isNotSent == true}">
            <div class="col-md-12 text-center alert alert-danger investFormInside">
                Email incorrect. Veuillez sélectionner l'email affilié à votre compte.
            </div>
            <br/>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <div class="col-md-6">
            <c:url var="resendPwdFormActionUrl" value="/authentification/resetPwd"/>
            <form id="eventForm" class="form-horizontal" action="${resendPwdFormActionUrl}"
                  modelAttribute="resendPwdFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Récupération du mot de passe</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">Veuillez indiquer l'adresse email associée à votre
                            compte</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email">
                        </div>
                    </div>

                    <br/>
                    <br/>
                    <div class="control-group">
                        <!-- Buttons -->
                        <div class="controls">
                            <center>
                                <button type="submit" class="btn btn-default">Récupérer le mot de passe</button>
                            </center>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>