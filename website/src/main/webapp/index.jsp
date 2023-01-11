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
    <s:include value="includes/menu.jsp"/>

    <div class="tr-front-banner">
        <h1>Triedge</h1>
        <img title="logo" src="includes/img/favicon.png" />
    </div>

    <div class="tr-content">
        <div class="tr-row">
            <div class="tr-col">
                <s:iterator var="i" step="1" value="articles">
                    <div class="tr-article-light">
                        <table>
                            <tr>
                                <td style="width: 60px">
                                    <div class="tr-article-thumbnail" style="background-image: url(<s:property value="thumbnail"></s:property>);"></div>
                                </td>
                                <td>
                                    <div class="tr-article-category"><s:property value="category.name"></s:property></div>
                                    <div class="tr-article-light-title">
                                        <a class="tr-link" href="article?strutsArticleId=<s:property value="id"></s:property>"><s:property value="title"></s:property></a>
                                    </div>
                                    <div class="tr-article-light-date"><s:date name = "date" format = "dd-MM-yyyy" /></div>
                                    <div class="tr-article-light-content"><s:property value="description"></s:property></div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </s:iterator>
            </div>
            <div class="tr-col">
                <div class="tr-user-block">
                    <div class="tr-user-image">
                        <img src="<s:property value="user.image"></s:property>"/>
                    </div>
                    <div class="tr-user-name"><s:property value="user.name"></s:property></div>
                    <div class="tr-user-desc"><s:property value="user.description"></s:property></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
