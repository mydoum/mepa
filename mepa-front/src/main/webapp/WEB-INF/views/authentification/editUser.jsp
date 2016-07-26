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

<style type="text/css">
    .pad-left-date {
        left: -80px;
    }

    .margin-bottom-date {
        margin-top: -25px;
    }
</style>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/editUser"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                  modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Modification du profil</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="firstname">Prénom</label>
                        <div class="controls">
                            <input id="firstname" name="firstname" placeholder="" class="form-control input-lg"
                                   type="text" value="${userCo.firstName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="lastname">Nom</label>
                        <div class="controls">
                            <input id="lastname" name="lastname" placeholder="" class="form-control input-lg"
                                   type="text" value="${userCo.lastName}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">Adresse e-mail</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email"
                                   value="${userCo.login}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Date de naissance</label>
                        <div class="controls">
                            <input type="text" class="form-control input-lg" name="date" value="${userCo.birthDate}" readonly/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="address">Adresse</label>
                        <div class="controls">
                            <input id="address" name="address" placeholder="" class="form-control input-lg" type="text"
                                   value="${userCo.address}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="description">Description</label>
                        <div class="controls">
                            <input id="description" name="description" placeholder="" class="form-control input-lg" type="text"
                                   value="${userCo.description}">
                        </div>
                    </div>
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

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>