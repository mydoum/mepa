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
    <c:if test="${isEdited == true}">
        <div class="col-md-12 text-center alert alert-success investFormInside">
            Votre profil a été modifié avec succès !
        </div>
    </c:if>
    <c:if test="${isNotEdited == true}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
            Erreur. Cette adresse email est déjà affiliée à un autre utilisateur.
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/editUser"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                  modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>

                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="#editUserTab">Modification du profil</a></li>
                        <li><a data-toggle="tab" href="#modifyPasswordTab">Changer de mot de passe</a></li>
                    </ul>
                    <div class="tab-content">
                    <div class="tab-pane fade in active" id="editUserTab">

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
                                   value="${userCo.login}" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Date de naissance</label>
                        <div class="controls">
                            <input type="text" class="form-control input-lg" name="date" value="${formatedBirthday}" readonly/>
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
                    <br/>
                    <br/>
                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Enregistrer</button>
                        </div>
                    </div>
                    </div>
                        <div id="modifyPasswordTab" class="tab-pane fade">
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
                                    <c:url var="addCustomUserFormActionUrl" value="/authentification/modifyPassword"/>
                                    <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                                          modelAttribute="addCustomUserFormBean"
                                          method="POST">
                                        <fieldset>


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

                </fieldset>

            </form>
        </div>
    </div>
</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/formValidation.min.js"></script>
<script src="http://formvalidation.io/vendor/formvalidation/js/framework/bootstrap.min.js"></script>
<!--
<script>
    function openModifyPasswordTab() {
        $('#modifyPasswordTab').load('modifyPwd.jsp')
    }
</script>-->