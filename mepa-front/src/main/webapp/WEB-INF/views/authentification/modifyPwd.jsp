<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 25/7/2016
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<div class="container">
    <c:if test="${newPassword == false}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
            Le mot de passe actuel est incorrect.
        </div>
    </c:if>
    <c:if test="${confPassword == false}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
            Le mot de passe de confirmation est incorrect.
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/modifyPassword"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                  modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Modification du mot de passe</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="password">Mot de passe actuel</label>
                        <div class="controls">
                            <input id="passwordOld" name="passwordOld" placeholder="" class="form-control input-lg"
                                   type="password" value="" pattern="[0-9a-zA-Z]{6,15}" required>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="control-group">
                        <label class="control-label" for="password">Nouveau mot de passe</label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="" class="form-control input-lg"
                                   type="password" value="" pattern="[0-9a-zA-Z]{6,15}" required>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="control-group">
                        <label class="control-label" for="password">Confirmation du nouveau mot de passe</label>
                        <div class="controls">
                            <input id="passwordConf" name="passwordConf" placeholder="" class="form-control input-lg"
                                   type="password" value="" pattern="[0-9a-zA-Z]{6,15}" required>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Enregistrer</button>
                        </div>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>
</div>
<!--
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $('#eventForm').formValidation({
            framework: 'bootstrap',
            fields: {
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The new password is required'
                        },
                        password: {
                            format: 'DD/MM/YYYY',
                            message: 'The date is not a valid'
                        }
                    }
                }
            }
        });
    });
</script>-->