<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<style type="text/css">
    .pad-left-date {
        left: -56px;
    }

    .margin-bottom-date {
        margin-top: -25px;
    }
</style>

<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<div class="col-md-6">--%>
<%--<c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>--%>
<%--<form data-toggle="validator" role="form" id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"--%>
<%--modelAttribute="addCustomUserFormBean"--%>
<%--method="POST">--%>
<%--<fieldset>--%>
<%--<div id="legend">--%>
<%--<legend class="">Inscription</legend>--%>
<%--</div>--%>
<%--<div class="control-group">--%>
<%--<label for="inputEmail" class="control-label">Adresse e-mail</label>--%>
<%--<input id="inputEmail" name="inputEmail" type="email" id="email" class="form-control" placeholder=""--%>
<%--data-error="Bruh, that email address is invalid" required>--%>
<%--<div class="help-block with-errors"></div>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<label for="inputPassword" class="control-label">Mot de passe</label>--%>
<%--<input type="password" data-minlength="6" class="form-control" id="inputPassword" name="inputPassword" placeholder="" required>--%>
<%--<div class="help-block"></div>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<label for="inputLastName" class="control-label">Nom</label>--%>
<%--<input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="">--%>
<%--<i class="help-block">(Facultatif)</i>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<label for="inputFirstName" class="control-label">Prénom</label>--%>
<%--<input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="">--%>
<%--<i class="help-block">(Facultatif)</i>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<label class="control-label">Date de naissance</label>--%>
<%--<div class="input-group input-append date pad-left-date" id="datePicker">--%>
<%--<input type="text" class="form-control" name="birthdate"/>--%>
<%--<span class="input-group-addon add-on">--%>
<%--<span class="glyphicon glyphicon-calendar"></span>--%>
<%--</span>--%>
<%--</div>--%>
<%--<i class="help-block margin-bottom-date">(Facultatif)</i>--%>
<%--</div>--%>

<%--<div class="control-group">--%>
<%--<!-- Button -->--%>
<%--<div class="controls">--%>
<%--<button type="submit" class="btn btn-default">Inscription</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</div>--%>
<%--<br/>--%>



<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

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
    <div id="signupbox" style="margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">S'inscrire</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Se connecter</a></div>
            </div>
            <div class="panel-body" >
                <c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>
                <form data-toggle="validator" role="form" id="signupform" class="form-horizontal" action="${addCustomUserFormActionUrl}"
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
                                <input id="emailInput" type="email" class="form-control" name="emailInput" placeholder="Adresse e-mail">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="passwordInput" class="col-md-3 control-label">Mot de passe</label>
                            <div class="col-md-9">
                                <input id="passwordInput" type="password" class="form-control" name="passwordInput" placeholder="Mot de passe">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="lastNameInput" class="col-md-3 control-label">Nom</label>
                            <div class="col-md-9">
                                <input id="lastNameInput" type="text" class="form-control" name="lastNameInput" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstNameInput" class="col-md-3 control-label">Prénom</label>
                            <div class="col-md-9">
                                <input id="firstNameInput" type="text" class="form-control" name="firstNameInput" placeholder="">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-3 control-label">Date de naissance</label>
                            <div class="col-md-9">
                                <div class="input-group input-append date pad-left-date" id="datePicker">
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
                                <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Inscription</button>
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

    <div class="col-md-12 investFormInside">
        <tr/>
        <div class="col-md-12 investFormInside">
            <div class="col-md-8"><h2>Utilisateurs</h2></div>
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
</div>