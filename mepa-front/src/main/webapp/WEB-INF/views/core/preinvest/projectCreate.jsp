<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.Date" %>

<%--
  Created by IntelliJ IDEA.
  User: Xavier
  Date: 21/07/2016
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>


<script>
    $(document).ready(function () {
        $('#datePicker1')
                .datepicker({
                    format: 'dd/mm/yyyy',
                    startDate: 'today',
                    endDate: '01/01/2100'
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
    });

    $(document).ready(function () {
        $('#datePicker2')
                .datepicker({
                    format: 'dd/mm/yyyy',
                    startDate: 'today',
                    endDate: '01/01/2100'
                })
                .on('changeDate', function (e) {
                    // Revalidate the date field
                    $('#eventForm').formValidation('revalidateField', 'date');
                });
    });




</script>


<script>
    $(function () {
        $(document).on('click', '.btn-add', function (e) {
            e.preventDefault();

            var controlForm = $('.controls form:first'),
                    currentEntry = $(this).parents('.entry:first'),
                    newEntry = $(currentEntry.clone()).appendTo(controlForm);

            newEntry.find('input').val('');
            controlForm.find('.entry:not(:last) .btn-add')
                    .removeClass('btn-add').addClass('btn-remove')
                    .removeClass('btn-success').addClass('btn-danger')
                    .html('<span class="glyphicon glyphicon-minus"></span>');
        }).on('click', '.btn-remove', function (e) {
            $(this).parents('.entry:first').remove();

            e.preventDefault();
            return false;
        });
    });

</script>



<div class="container">
    <c:if test="${is_connected}">
    <c:if test="${is_unique}">
    <div class="alert alert-warning">
        Ce nom de projet est déjà pris : veuillez choisir un autre nom
    </div>
    </c:if>
    <c:if test="${is_null}">
    <div class="alert alert-warning">
        Vous devez rentrez un nom de projet pour créer le projet
    </div>
    </c:if>
    <c:if test="${is_date}">
    <div class="alert alert-warning">
        La date de fin ne peut être antérieur à la date de début
    </div>
    </c:if>

    <div class="well bs-component">
        <sf:form method="post" modelAttribute="newProject" action="processCreation" onsubmit="save();">
        <div class="control-group">
            <label class="control-label">Nom du projet</label>

            <div class="controls">
                <td><form:input path="Name" class="form-control input-lg" placeholder="Définissez le nom du projet"
                                required="required" value="${project.name}"/></td>
            </div>
        </div>
        <br/>


        <div class="control-group">
            <label class="control-label">Date de début</label>

            <div class="controls">
                <div class="input-group input-append date" id="datePicker1">
                    <td><form:input path="startDate" class="form-control input-lg" placeholder="jj/mm/aaaa"
                                    value='${project.dateFormat(\"dd/MM/YYYY\", project.startDate)}'
                                    required="required" /></td>
                                    <span class="input-group-addon add-on">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                </div>
            </div>
        </div>
        <br/>

        <div class="control-group">
            <label class="control-label">Date de fin</label>

            <div class="controls">
                <div class="input-group input-append date" id="datePicker2">
                    <td><form:input path="endDate" class="form-control input-lg" placeholder="jj/mm/aaaa"
                                    required="required" value="${project.dateFormat(\"dd/MM/YYYY\", project.endDate)}"/></td>
                                    <span class="input-group-addon add-on">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                </div>
            </div>
        </div>

        <br/>

        <div class="control-group">
            <label class="control-label">Objectif du projet en </label>
            <label>
                <input name="currency" id="dollar" value="DOLLAR" checked="" type="radio">
                $
            </label>

            <label>
                <input name="currency" id="euro" value="EURO" type="radio">
                €
            </label>

            <input name="currency" id="pound" value="POUND" type="radio">
            £

            <div class="controls">
                <td><form:input name="goal" type="number" path="goalAmount" class="form-control input-lg"
                                placeholder="Entrez la somme à atteindre" required="required" value="${project.goalAmount}"/></td>
            </div>
        </div>
        <br/>

        <div class="control-group">
            <label class="control-label">Description</label><label class="text-muted"> - Facultatif</label>

            <div class="controls">
                <td><form:textarea name="desription" path="description" class="form-control input-lg" placeholder="Description"
                                   style="margin-top: 0px;" value="${project.description}"/></td>
            </div>
        </div>
        <br/>

        <div class="control-group">
            <label class="control-label">Image</label><label class="text-muted"> - Facultatif</label>

            <div class="controls">
                <input name="imageUrl" class="form-control input-lg" placeholder="URL pour insérer une image"/>
            </div>

            <form action="http://www.html.am/html-codes/forms/html-form-tag-action.cfm" target="result" method="get">

                <br/>

                <div class="control-group">
                    <label class="control-label">Partager son projet</label><label class="text-muted">
                    - Facultatif</label>

                    <div class="checkbox">
                        <label>
                            <form:checkbox path="facebookAllowed"/> Facebook
                        </label>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="twitterAllowed"/> Twitter
                        </label>
                    </div>
                    <br/>

                    <div class="control-group">
                        <button type="submit" class="btn btn-primary">Créer le projet</button>
                    </div>
                    </sf:form>
                </div>

                </c:if>
                <c:if test="${!is_connected}">
                    <h1> Vous devez être connecté pour ajouter un projet.</h1>
                </c:if>
            </form>
        </div>
</div>

