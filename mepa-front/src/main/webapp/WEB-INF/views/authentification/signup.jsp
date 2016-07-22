<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 21/7/2016
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<style type="text/css">
    /**
     * Override feedback icon position
     * See http://formvalidation.io/examples/adjusting-feedback-icon-position/
     */
    #eventForm .form-control-feedback {
        top: 0;
        right: -15px;
    }
</style>


<script>
    $(document).ready(function() {
        $('#datePicker')
                .datepicker({
                    format: 'dd/mm/yyyy'
                })
                .on('changeDate', function(e) {
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

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/addUser"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}" modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Inscription</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="lastname">Nom</label>
                        <div class="controls">
                            <input id="lastname" name="lastname" placeholder="" class="form-control input-lg" type="text">
                            <p class="help-block">Nom can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="firstname">Prénom</label>
                        <div class="controls">
                            <input id="firstname" name="firstname" placeholder="" class="form-control input-lg" type="text">
                            <p class="help-block">Prénom can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">Adresse e-mail</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email">
                            <p class="help-block">Please provide your e-mail</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Mot de passe</label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="" class="form-control input-lg" type="password">
                            <p class="help-block">Password should be at least 6 characters</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password_confirm">Mot de passe (Confirmation)</label>
                        <div class="controls">
                            <input id="password_confirm" name="password_confirm" placeholder="" class="form-control input-lg" type="password">
                            <p class="help-block">Please confirm password</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="firstname">Date de naissance</label>
                        <div class="controls">
                            <input id="birthdate" name="birthdate" placeholder="" class="form-control input-lg" type="text">
                            <p class="help-block">Birthday</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="col-xs-3 control-label">Date</label>
                        <div class="col-xs-5 date">
                            <div class="input-group input-append date" id="datePicker">
                                <input type="text" class="form-control" name="date" />
                                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
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
    </div>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>