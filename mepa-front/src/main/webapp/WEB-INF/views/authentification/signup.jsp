<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>

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

    .margin-date-bottom {
        margin-top: -25px;
    }

</style>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/addUser"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                  modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Inscription</legend>
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

                    <div class="control-group">
                        <label class="control-label" for="password_confirm">Confirmation du mot de passe</label>
                        <div class="controls">
                            <input id="password_confirm" name="password_confirm" placeholder=""
                                   class="form-control input-lg" type="password">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="lastname">Nom</label>
                        <div class="controls">
                            <input id="lastname" name="lastname" placeholder="" class="form-control input-lg"
                                   type="text">
                        </div>
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="firstname">Prénom</label>
                        <div class="controls">
                            <input id="firstname" name="firstname" placeholder="" class="form-control input-lg"
                                   type="text">
                        </div>
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <label class="control-label">Date de naissance</label>
                            <div class="input-group input-append date pad-left-date" id="datePicker">
                                <input type="text" class="form-control input-lg" name="birthdate"/>
                                <span class="input-group-addon add-on">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                            </div>
                            <i class="help-block margin-date-bottom">(Facultatif)</i>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Inscription</button>
                        </div>
                    </div>

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
                    format: 'dd/mm/yyyy'
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });

        $('#eventForm').formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                date: {
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                        date: {
                            format: 'DD/MM/YYYY',
                            message: 'The date is not a valid'
                        }
                    }
                }
            }
        });
    });
</script>