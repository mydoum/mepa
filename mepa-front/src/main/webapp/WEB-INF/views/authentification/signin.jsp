<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <c:if test="${pwdFalse == true}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
            La connexion a échouée : Mot de passe ou Identifiant incorrect.
        </div>
    </c:if>
    <c:if test="${not empty messageRedirect}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
                ${messageRedirect}
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-6">
            <c:url var="loginUserFormActionUrl" value="/authentification/signin"/>
            <form id="eventForm" class="form-horizontal" action="${loginUserFormActionUrl}"
                  modelAttribute="loginUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Connexion</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">Adresse e-mail</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="password">Mot de passe</label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="" class="form-control input-lg"
                                   type="password">
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="control-group">
                        <!-- Buttons -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Connexion</button>
                            <a class="btn" href="/authentification/resendPwd">Mot de passe oublié ?</a>
                        </div>
                    </div>

                </fieldset>

            </form>
        </div>
    </div>
</div>