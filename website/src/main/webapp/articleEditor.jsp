<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - Editor</title>
    <head>
        <s:include value="includes/header.jsp"/>
    </head>
</head>
<body>
    <s:include value="includes/menu.jsp"/>
    <script src="includes/js/editor.js"></script>

    <div class="tr-hidden-separator"></div>

    <div class="tr-content tr-padding">
        <div>
            <span onclick=""></span>
        </div>
        <textarea id="editor"></textarea>
    </div>
</body>
</html>
