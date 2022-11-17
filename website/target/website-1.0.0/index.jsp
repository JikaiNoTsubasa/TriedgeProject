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

    <div class="tr-front-banner">
        <h1>Triedge</h1>
        <img title="logo" src="includes/img/favicon.png" />
    </div>

    <div class="tr-content">
        <div class="tr-article-column">
            <s:iterator var="i" step="1" value="articles">
                <div class="tr-article-light">
                    <img class="tr-article-thumbnail" src="<s:property value="thumbnail"></s:property>" />
                    <div class="tr-article-light-title"><s:property value="title"></s:property></div>
                    <div class="tr-article-light-category"><s:property value="category.name"></s:property></div>
                    <div class="tr-article-light-content"><s:property value="contentShrinked"></s:property></div>
                    <a href="article?strutsArticleId=<s:property value="id"></s:property>">Open</a>
                </div>
            </s:iterator>
        </div>
    </div>
</body>
</html>
