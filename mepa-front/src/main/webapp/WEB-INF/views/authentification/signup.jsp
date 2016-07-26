<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<%--<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>--%>
<%--<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>--%>

<style type="text/css">
    /**
     * Override feedback icon position
     * See http://formvalidation.io/examples/adjusting-feedback-icon-position/
     */
    #eventForm .form-control-feedback {
        top: 0;
        right: -15px;
    }

    .pad-left-date {
        left: -83px;
    }

    .margin-bottom-date {
        margin-top: -25px;
    }

</style>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <%--<c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>--%>
            <%--<form data-toggle="validator" role="form" id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"--%>
                  <%--modelAttribute="addCustomUserFormBean"--%>
                  <%--method="POST">--%>
                <%--<fieldset>--%>
                    <%--<div id="legend">--%>
                        <%--<legend class="">Inscription</legend>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<label class="control-label" for="email">Adresse e-mail</label>--%>
                        <%--<div class="controls">--%>
                            <%--<input id="email" name="email" placeholder="" class="form-control input-lg" type="email" data-error="Bruh, that email address is invalid" required>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<label class="control-label" for="password">Mot de passe</label>--%>
                        <%--<div class="controls">--%>
                            <%--<input id="password" name="password" placeholder="" class="form-control input-lg"--%>
                                   <%--type="password">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<label class="control-label" for="lastname">Nom</label>--%>
                        <%--<div class="controls">--%>
                            <%--<input id="lastname" name="lastname" placeholder="" class="form-control input-lg"--%>
                                   <%--type="text">--%>
                        <%--</div>--%>
                        <%--<i class="help-block">(Facultatif)</i>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<label class="control-label" for="firstname">Prénom</label>--%>
                        <%--<div class="controls">--%>
                            <%--<input id="firstname" name="firstname" placeholder="" class="form-control input-lg"--%>
                                   <%--type="text">--%>
                        <%--</div>--%>
                        <%--<i class="help-block">(Facultatif)</i>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<div class="controls">--%>
                            <%--<label class="control-label">Date de naissance</label>--%>
                            <%--<div class="input-group input-append date pad-left-date" id="datePicker">--%>
                                <%--<input type="text" class="form-control input-lg" name="birthdate"/>--%>
                                <%--<span class="input-group-addon add-on">--%>
                                <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                            <%--<i class="help-block margin-bottom-date">(Facultatif)</i>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="control-group">--%>
                        <%--<!-- Button -->--%>
                        <%--<div class="controls">--%>
                            <%--<button type="submit" class="btn btn-default">Inscription</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                <%--</fieldset>--%>
            <%--</form>--%>


            <%-- ghjkldsjgjskfhjkdsjkfhdsjkfhjdkshfjkdshfjkdshfjkdshfjksdhjkfhdsjkfhdsjkfhdsjkfhjkdshfjkds --%>

                <c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>
                <form data-toggle="validator" role="form" id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                    modelAttribute="addCustomUserFormBean"
                    method="POST">
            <%--<form data-toggle="validator" role="form" method="POST">--%>
                <fieldset>
                    <div id="legend">
                        <legend class="">Inscription</legend>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="control-label">Adresse e-mail</label>
                        <input id="inputEmail" name="inputEmail" type="email" class="form-control" placeholder="Email"
                               data-error="Bruh, that email address is invalid" required>
                        <div class="help-block with-errors"></div>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword" class="control-label">Mot de passe</label>
                        <div class="form-inline row">
                            <div class="form-group col-sm-6">
                                <input type="password" data-minlength="6" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password" required>
                                <div class="help-block"></div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName" class="control-label">Nom</label>
                        <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Saffary">
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="form-group">
                        <label for="inputFirstName" class="control-label">Prénom</label>
                        <input type="text" class="form-control" id="inputFirstName" name="inputLastName" placeholder="Cina">
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Date de naissance</label>
                        <div class="input-group input-append date pad-left-date" id="datePicker">
                            <input type="text" class="form-control input-lg" name="birthdate"/>
                            <span class="input-group-addon add-on">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <i class="help-block margin-bottom-date">(Facultatif)</i>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Inscription</button>
                        </div>
                    </div>
                <%--<div class="form-group has-feedback">--%>
                    <%--<label for="inputTwitter" class="control-label">Twitter</label>--%>
                    <%--<div class="input-group">--%>
                        <%--<span class="input-group-addon">@</span>--%>
                        <%--<input type="text" pattern="^[_A-z0-9]{1,}$" maxlength="15" class="form-control" id="inputTwitter" placeholder="1000hz" required>--%>
                    <%--</div>--%>
                    <%--<span class="glyphicon form-control-feedback" aria-hidden="true"></span>--%>
                    <%--<div class="help-block with-errors">Hey look, this one has feedback icons!</div>--%>
                <%--</div>--%>


                <%--<div class="form-group">--%>
                    <%--<div class="radio">--%>
                        <%--<label>--%>
                            <%--<input type="radio" name="underwear" required>--%>
                            <%--Boxers--%>
                        <%--</label>--%>
                    <%--</div>--%>
                    <%--<div class="radio">--%>
                        <%--<label>--%>
                            <%--<input type="radio" name="underwear" required>--%>
                            <%--Briefs--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<div class="checkbox">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" id="terms" data-error="Before you wreck yourself" required>--%>
                            <%--Check yourself--%>
                        <%--</label>--%>
                        <%--<div class="help-block with-errors"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
                <%--</div>--%>
                </fieldset>
            </form>
        </div>
        <br/>
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
        <br/>
    </div>
</div>

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
//        $('#eventForm').formValidation({
//            framework: 'bootstrap',
//            icon: {
//                valid: 'glyphicon glyphicon-ok',
//                invalid: 'glyphicon glyphicon-remove',
//                validating: 'glyphicon glyphicon-refresh'
//            },
//            fields: {
//                email: {
//                    validators: {
//                        notEmpty: {
//                            message: "L'email est obligatoire"
//                        },
//                    }
//                },
//                password: {
//                    validators: {
//                        notEmpty: {
//                            message: "Le mot de passe est obligatoire"
//                        },
//                        regexp: {
//                            regexp: /^[a-z\s]+$/i,
//                            message: 'Le mot de passe ne peut être constituer que de caractère alphanumérique'
//                        },
//                        stringLength: {
//                            min: 6,
//                            max: 20,
//                            message: 'Le mot de passe doit être entre 6 et 20 caractère'
//                        }
//                    }
//                },
//                birthdate: {
//                    validators: {
//                        birthdate: {
//                            format: 'DD/MM/YYYY',
//                            min: '01/01/1900',
//                            max: 'today',
//                            message: "La date n'est pas valide"
//                        }
//                    }
//                }
//            }
//        });

</script>
