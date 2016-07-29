<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#editUserTab">Authentification</a></li>
                <li><a data-toggle="tab" href="#modifyPasswordTab">Commentaires</a></li>
                <li><a data-toggle="tab" href="#project">Projets</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="editUserTab">
                    <form id="eventForm" class="form-horizontal">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label" for="firstname">Nombre de vues sur la page
                                    d'Inscription</label>
                                <div class="controls">
                                    <input id="firstname" name="firstname" placeholder="" class="form-control input-lg"
                                           type="text" value="${nbViewInscription}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="lastname">Nombre d'Inscription</label>
                                <div class="controls">
                                    <input id="lastname" name="lastname" placeholder="" class="form-control input-lg"
                                           type="text" value="${nbInscription}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">Nombre de vue sur la page de Connexion</label>
                                <div class="controls">
                                    <input id="email" name="email" placeholder="" class="form-control input-lg"
                                           type="email"
                                           value="${nbViewLogin}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="address">Nombre de connexions</label>
                                <div class="controls">
                                    <input id="address" name="address" placeholder="" class="form-control input-lg"
                                           type="text"
                                           value="${nbLogin}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de vue sur la page de renvoi de
                                    mot de passe</label>
                                <div class="controls">
                                    <input id="description" name="description" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbViewForget}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de renvoi de mot de passe</label>
                                <div class="controls">
                                    <input id="lol" name="description" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbForget}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de vue sur l'édition de
                                    profil</label>
                                <div class="controls">
                                    <input id="loli" name="description" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbViewEditUser}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre d'édition de profil</label>
                                <div class="controls">
                                    <input id="lola" name="description" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbEditUser}" disabled>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div id="modifyPasswordTab" class="tab-pane fade">
                    <form id="eventForm1" class="form-horizontal">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de commentaires</label>
                                <div class="controls">
                                    <input id="comments" name="comments" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbComments}" disabled>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div id="project" class="tab-pane fade">
                    <form id="eventForm2" class="form-horizontal">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de vues sur la création de projet</label>
                                <div class="controls">
                                    <input id="viewProjectCreate" name="comments" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbViewProjectCreate}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de projets créés</label>
                                <div class="controls">
                                    <input id="nbProjectCreate" name="comments" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbProjectCreate}" disabled>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">Nombre de projets visités</label>
                                <div class="controls">
                                    <input id="nbViewProject" name="comments" placeholder=""
                                           class="form-control input-lg" type="text"
                                           value="${nbViewProject}" disabled>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
