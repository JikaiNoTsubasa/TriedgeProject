<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge</title>
    <head>
        <s:include value="includes/header.jsp"/>
    </head>
</head>
<body>

<div class="tr-hidden-separator"></div>

<div class="tr-content tr-padding">
    <s:form action="loginAuthentication">
        <s:textfield label="UserName" name="strutsUserName" />
        <s:password label="Password" name="strutsPassword" />

        <s:submit label="Login" name="submit" />
    </s:form>

</div>
</body>
</html>