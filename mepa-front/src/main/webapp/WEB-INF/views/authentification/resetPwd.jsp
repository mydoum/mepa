<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 29/7/2016
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
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
            <c:url var="addCustomUserFormActionUrl" value="/authentification/changePwd"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                  modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="modifyPasswordTab">
                        <div id="legend">
                            <legend class="">Modification du mot de passe</legend>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="newPassword">Nouveau mot de passe</label>
                            <div class="controls">
                                <input id="newPassword" name="newPassword" placeholder="" class="form-control input-lg"
                                       type="password" value="" pattern="[0-9a-zA-Z]{6,15}" required>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="passwordConf">Confirmation du nouveau mot de passe</label>
                            <div class="controls">
                                <input id="passwordConf" name="passwordConf" placeholder="" class="form-control input-lg"
                                       type="password" value="" pattern="[0-9a-zA-Z]{6,15}" required>
                            </div>
                        </div>
                        <div hidden>
                            <input id="hidden-user-id" name="hidden-user-id" class="form-control input-lg"
                                   type="text" value="${hiddenuserid}">
                        </div>
                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button type="submit" class="btn btn-default">Réinitialiser mon mot de passe</button>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
