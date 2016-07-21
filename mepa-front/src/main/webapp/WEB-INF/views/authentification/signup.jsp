<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 21/7/2016
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- Include Bootstrap Datepicker -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

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
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6">

            <form class="form-horizontal" action="" method="POST">
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
                        <label class="control-label">Anniversaire</label>
                        <div class="date">
                            <div class="input-group input-append date" id="datePicker">
                                <input type="text" class="form-control" name="date" />
                                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <p class="help-block">Please provide your Birthday</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success">Inscription</button>
                        </div>
                    </div>

                </fieldset>

            </form>
        </div>
    </div>
</div>

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
                name: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required'
                        }
                    }
                },
                date: {
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                        date: {
                            format: 'MM/DD/YYYY',
                            message: 'The date is not a valid'
                        }
                    }
                }
            }
        });
    });
</script>