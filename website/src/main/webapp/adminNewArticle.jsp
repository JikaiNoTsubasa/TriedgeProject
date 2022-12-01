<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - New Article</title>
    <head>
        <s:include value="includes/header.jsp"/>
        <script src="includes/js/bbcode.js"></script>
    </head>
</head>
<body>
<s:include value="includes/menu.jsp"/>
<div class="tr-hidden-separator"></div>
<div class="tr-content tr-padding">
    <ul class="tr-breadcrumb">
        <li><a href="admin">Admin</a></li>
        <li>New Article</li>
    </ul>
    <h1>New Article</h1>
    <span class="tr-button" id="btnDraft">Save Draft</span>
    <br>
    <br>
    <div class="tr-article-toolbar">
        <span onclick="insertBBCode('t1','area');">T1</span>
        <span onclick="insertBBCode('t2','area');">T2</span>
        <span onclick="insertBBCode('b','area');">B</span>
        <span onclick="insertBBCode('i','area');">I</span>
        <span onclick="insertBBCode('u','area');">U</span>
        <span onclick="insertBBCode('url=','url','area');">URL</span>
        <span onclick="generateHTML();">Generate</span>
    </div>
    <s:form style="width:100%">
        <s:textfield class="w100" name="articleTitle" id="title"></s:textfield>
        <br>
        <s:textarea class="w100 h500" name="articleContent" id="area"></s:textarea>
    </s:form>
    <div style="width: 100%" id="result"></div>
</div>
<script>
    $("#area").bind('input propertychange', function() {
        $("#btnDraft").html("Save Draft*");
    });

    $("#btnDraft").click(function(){
        ajaxSaveTemp();
    });

    function ajaxSaveTemp(){
        let tt = 'ArticleDraft';
        if ($("#title").val()){
            tt = $("#title").val();
        }
        $.ajax({
            url: 'ajaxtemp',
            method: 'get',
            data: {
                strutsAction: 'save',
                strutsUserId: '<s:property value="user.id"></s:property>',
                strutsKey: tt,
                strutsContent: $("#area").val()
            },
            success: function(response){
                notify("Draft saved!");
                $("#btnDraft").html("Save Draft");
            }
        });
    }

    function generateHTML(){
        let text = $("#area").val();
        let html = parseBBCode(text);
        $("#result").html(html);
    }
</script>
</body>
</html>
