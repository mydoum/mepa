<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<html>
<head>
    <%-- Page title --%>
    <c:set var="titleKey"><tiles:insertAttribute name="title"/></c:set>
    <title><fmt:message key="${titleKey}"/></title>

    <%-- Bootstrap CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <%-- Application CSS --%>
    <c:url var="defaultCssUrl" value="/css/default.css"/>
    <link rel="stylesheet" href="${defaultCssUrl}" type="text/css"/>

    <%-- jQuery --%>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <%-- Bootstrap JavaScript --%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
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