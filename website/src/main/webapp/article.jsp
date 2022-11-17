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
    <s:include value="/includes/menu.jsp"/>

    <div class="tr-content">
        <h1><s:property value="article.title"></s:property></h1>
        <div class="tr-category">Category</div>
    </div>
</body>
</html>
