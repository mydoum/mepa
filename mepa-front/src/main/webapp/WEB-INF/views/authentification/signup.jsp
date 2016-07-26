<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<style type="text/css">
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
                <c:url var="addCustomUserFormActionUrl" value="/authentification/createUser"/>
                <form data-toggle="validator" role="form" id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}"
                    modelAttribute="addCustomUserFormBean"
                    method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Inscription</legend>
                    </div>
                    <div class="control-group">
                        <label for="inputEmail" class="control-label">Adresse e-mail</label>
                        <input id="inputEmail" name="inputEmail" type="email" class="form-control" placeholder=""
                               data-error="Bruh, that email address is invalid" required>
                        <div class="help-block with-errors"></div>
                    </div>

                    <div class="control-group">
                        <label for="inputPassword" class="control-label">Mot de passe</label>
                                <input type="password" data-minlength="6" class="form-control" id="inputPassword" name="inputPassword" placeholder="" required>
                                <div class="help-block"></div>
                    </div>

                    <div class="control-group">
                        <label for="inputLastName" class="control-label">Nom</label>
                        <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="">
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="control-group">
                        <label for="inputFirstName" class="control-label">Prénom</label>
                        <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="">
                        <i class="help-block">(Facultatif)</i>
                    </div>

                    <div class="control-group">
                        <label class="control-label">Date de naissance</label>
                        <div class="input-group input-append date pad-left-date" id="datePicker">
                            <input type="text" class="form-control" name="birthdate"/>
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

</script>
