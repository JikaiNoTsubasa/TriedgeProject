<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - Article</title>
    <head>
        <s:include value="/includes/header.jsp"/>
    </head>
</head>
<body>
    <div class="tr-content">
        <h1><s:property value="article.title"></s:property></h1>
    </div>
</body>
</html>
