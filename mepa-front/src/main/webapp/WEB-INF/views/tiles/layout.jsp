<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<html>
<head>
    <%-- Page title --%>
    <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
    <fmt:message key="${titleKey}" var="titleVar"/>
    <c:choose>
        <c:when test="${titleVar=='This is a project!'}">
            <title>${project.name}</title>
        </c:when>
        <c:otherwise>
            <title>${titleVar}</title>
        </c:otherwise>
    </c:choose>


    <%-- Facebook meta characters--%>
    <meta property="og:url"           content="https://mepa.herokuapp.com/core/preinvest/projectDisplay/${project.id}" />
    <meta property="og:type"          content="projet" />
    <meta property="og:title"         content="${project.name}" />
    <meta name="description"          content="${project.description}" />
    <meta name="og:description"       content="${project.description}" />
    <meta property="og:image"         content="${project.imagesLinks[0]}" />

    <%-- Bootstrap CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <%-- Application CSS --%>
    <c:url var="defaultCssUrl" value="/css/default.css"/>
    <link rel="stylesheet" href="${defaultCssUrl}" type="text/css"/>
    <c:url var="projectDisplay" value="/css/project-display.css"/>
    <link rel="stylesheet" href="${projectDisplay}" type="text/css"/>

    <%-- Investissement CSS --%>
    <c:url var="investDefaultCss" value="/css/investment/investment.css"/>
    <link rel="stylesheet" href="${investDefaultCss}" type="text/css"/>
    <c:url var="investSliderCss" value="/css/investment/nouislider.min.css"/>
    <link rel="stylesheet" href="${investSliderCss}" type="text/css"/>

    <%-- jQuery --%>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <%-- Bootstrap JavaScript --%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <%-- Investissement javascript --%>
    <c:url var="investSliderJs" value="/js/investment/nouislider.min.js"/>
    <script src="${investSliderJs}"></script>
    <c:url var="investSliderPersoJs" value="/js/investment/slider.js"/>
    <script src="${investSliderPersoJs}"></script>

    <%-- SDK Facebook --%>
    <div id="fb-root"></div>
    <script>
        (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s);
            js.id = id;
            js.src = "//connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v2.7";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
    </script>
</head>
<body>
<%-- Header --%>
<tiles:insertAttribute name="header"/>
<%-- Body content --%>
<tiles:insertAttribute name="body"/>
<%-- Footer --%>
<tiles:insertAttribute name="footer"/>
</body>
</html>
