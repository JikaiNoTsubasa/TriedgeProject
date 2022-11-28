<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - Article: <s:property value="article.title"></s:property></title>
    <head>
        <s:include value="includes/header.jsp"/>
        <meta property="og:title" content="<s:property value="article.title"></s:property>">
        <meta property="og:type" content="article" />
        <meta property="og:description" content="<s:property value="article.contentShrinked"></s:property>">
        <meta property="og:image" content="<s:property value="article.thumbnail"></s:property>">
        <meta property="og:url" content="http://triedge.ovh">
        <meta name="twitter:card" content="summary_large_image">
    </head>
</head>
<body>
<!--
https://stackoverflow.com/questions/33426752/linkedin-share-post-url
http://www.earthfluent.com/social-share-media.html
-->
    <s:include value="includes/menu.jsp"/>
    <div class="tr-hidden-separator"></div>
    <div class="tr-content tr-padding">
        <ul class="tr-breadcrumb">
            <li><a href="index">Home</a></li>
            <li>Article</li>
            <li><s:property value="article.id"></s:property></li>
        </ul>
        <h1><s:property value="article.title"></s:property></h1>
        <div class="tr-category">Category</div>

        <a class="tr-link" target="_blank" href="https://www.linkedin.com/shareArticle?mini=true&url=http%3A%2F%2Ftriedge.ovh">Linked in</a>
    </div>
</body>
</html>
