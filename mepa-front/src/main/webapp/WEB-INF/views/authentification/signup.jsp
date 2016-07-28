<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>


<script>
    $(document).ready(function () {
        $('#datePicker')
                .datepicker({
                    format: 'dd/mm/yyyy',
                    startDate: '01/01/1900',
                    endDate: '26/07/2016'
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
    });

</script>

<div class="container">
    <c:if test="${pwdFalse == true}">
        <div class="col-md-12 text-center alert alert-danger col-sm-12">
            La connexion a échouée : Mot de passe ou Identifiant incorrect.
        </div>
    </c:if>
    <c:if test="${isNotCreated == true}">
        <div class="col-md-12 text-center alert alert-danger col-sm-12">
            Erreur. L'adresse email est déjà affiliée à un autre utilisateur.
        </div>
    </c:if>
    <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">S'inscrire</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#"
                                                                                           onclick="$('#signupbox').hide(); $('#loginbox').show()">Se
                    connecter</a></div>
            </div>
            <div class="panel-body">
                <c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>
                <form data-toggle="validator" role="form" id="signupform" class="form-horizontal"
                      action="${addCustomUserFormActionUrl}"
                      modelAttribute="addCustomUserFormBean" role="form"
                      method="POST">
                    <fieldset>

                        <div id="signupalert" style="display:none" class="alert alert-danger">
                            <p>Error:</p>
                            <span></span>
                        </div>


                        <div class="form-group">
                            <label for="email" class="col-md-3 control-label">Adresse e-mail</label>
                            <div class="col-md-9">
                                <input id="emailInput" type="email" class="form-control" name="emailInput"
                                       placeholder="Adresse e-mail">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="passwordInput" class="col-md-3 control-label">Mot de passe</label>
                            <div class="col-md-9">
                                <input id="passwordInput" type="password" class="form-control" name="passwordInput"
                                       placeholder="Mot de passe">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="lastNameInput" class="col-md-3 control-label">Nom</label>
                            <div class="col-md-9">
                                <input id="lastNameInput" type="text" class="form-control" name="lastNameInput"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstNameInput" class="col-md-3 control-label">Prénom</label>
                            <div class="col-md-9">
                                <input id="firstNameInput" type="text" class="form-control" name="firstNameInput"
                                       placeholder="">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">Date de naissance</label>
                            <div class="col-md-9">
                                <div class="input-group input-append date" id="datePicker">
                                    <input type="text" class="form-control" name="birthdate"/>
                                    <span class="input-group-addon add-on">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <!-- Button -->
                            <div class="col-md-offset-3 col-md-9">
                                <button id="btn-signup" type="submit" class="btn btn-info"><i
                                        class="icon-hand-right"></i> &nbsp S'inscrire
                                </button>
                                <%--<span style="margin-left:8px;">or</span>--%>
                            </div>
                        </div>

                        <%-- FIXME --%>
                        <%--<div style="border-top: 1px solid #999; padding-top:20px"  class="form-group">--%>

                        <%--<div class="col-md-offset-3 col-md-9">--%>
                        <%--<button id="btn-fbsignup" type="button" class="btn btn-primary"><i class="icon-facebook"></i>   Sign Up with Facebook</button>--%>
                        <%--</div>--%>

                        <%--</div>--%>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
    <div id="loginbox" style="display:none ; margin-top:50px;"
         class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Se connecter</div>
                <div style="float:right; font-size: 80%; position: relative; top:-10px"><a
                        href="/authentification/resendPwd">Mot de passe oublié ?</a></div>
            </div>

            <div style="padding-top:30px" class="panel-body">

                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                <c:url var="loginUserFormActionUrl" value="/authentification/signin"/>
                <form id="loginform" class="form-horizontal" role="form" action="${loginUserFormActionUrl}"
                      modelAttribute="loginUserFormBean"
                      method="POST">
                    <%--<form id="loginform" class="form-horizontal" role="form">--%>
                    <fieldset>
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="inputEmail" type="email" class="form-control" name="inputEmail" value=""
                                   placeholder="Adresse e-mail">
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-inputPassword" type="password" class="form-control" name="inputPassword"
                                   placeholder="Mot de passe">
                        </div>
                        
                        <div style="margin-top:10px" class="form-group">
                            <!-- Button -->
                            <div class="col-sm-12 controls">
                                <button id="btn-login" type="submit" class="btn btn-success">Connexion</button>
                                <%--<a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>--%>
                            </div>
                        </div>
                    </fieldset>
                </form>

            </div>
        </div>
    </div>
</div>