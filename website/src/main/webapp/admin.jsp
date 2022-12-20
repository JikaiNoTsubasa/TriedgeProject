<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - Admin Panel</title>
    <head>
        <s:include value="includes/header.jsp"/>
    </head>
</head>
<body>
<s:include value="includes/menu.jsp"/>
<div class="tr-hidden-separator"></div>
<div class="tr-content tr-padding">
    <ul class="tr-breadcrumb">
        <li><a href="admin">Admin</a></li>
        <li>Home</li>
    </ul>
    <h1>Admin</h1>
    <span>Bienvenue <s:property value="user.name"></s:property>.</span>
    <br>
    Vous avez <span class="tr-text-highlight"><s:property value="articleNumber"></s:property></span> articles publiés.<br>
    Vous avez <span class="tr-text-highlight"><s:property value="draftNumber"></s:property></span> drafts.
    <br>
    <br>
    <a class="tr-button" href="admin?strutsAction=newArticle">New Article</a>
    <div class="tr-scroll">
        <table>
            <tr>
                <th>Title</th>
                <th>Category</th>
                <th>Author</th>
                <th>Action</th>
            </tr>
            <s:iterator step="1" var="i" value="articles">
                <tr>
                    <td><s:property value="title"></s:property></td>
                    <td><s:property value="category.name"></s:property></td>
                    <td><s:property value="user.name"></s:property></td>
                    <td><a class="tr-link" href="admin?strutsAction=editArticle&strutsArticleId=<s:property value="id"></s:property>">Edit</a></td>
                </tr>
            </s:iterator>
        </table>
    </div>
    <br>
    <br>
    <h3>Drafts:</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Action</th>
        </tr>
        <s:iterator step="1" var="i" value="drafts">
            <tr>
                <td><s:property value="id"></s:property></td>
                <td><s:property value="title"></s:property></td>
                <td><a class="tr-link" href="admin?strutsAction=editArticle&strutsArticleId=<s:property value="id"></s:property>">Edit</a> <a class="tr-link" href="admin?strutsAction=deleteArticle&strutsArticleId=<s:property value="id"></s:property>">Delete</a></td>
            </tr>
        </s:iterator>
    </table>
</div>
</body>
</html>
