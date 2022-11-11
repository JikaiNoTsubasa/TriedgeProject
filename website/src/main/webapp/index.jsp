<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge</title>
    <head>
        <s:include value="/includes/header.jsp"/>
    </head>
</head>
<body>
    <s:include value="/includes/menu.jsp"/>
    <div class="tr-content">
        <s:iterator var="i" step="1" value="articles">
            <div class="tr-article-light">
                <img class="tr-article-thumbnail" src="<s:property value="thumbnail"></s:property>" />
                <div class="tr-article-light-title"><s:property value="title"></s:property></div>
                <div class="tr-article-light-category"><s:property value="category.name"></s:property></div>
                <div class="tr-article-light-content"><s:property value="contentShrinked"></s:property></div>
            </div>
        </s:iterator>
    </div>
</body>
</html>
