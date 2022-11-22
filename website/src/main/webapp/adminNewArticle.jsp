<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - New Article</title>
    <head>
        <s:include value="includes/header.jsp"/>
    </head>
</head>
<body>
<s:include value="includes/menu.jsp"/>
<div class="tr-hidden-separator"></div>
<div class="tr-content tr-padding">
    <h1>New Article</h1>
    <span class="tr-button" id="btnDraft">Save Draft</span>
    <br>
    <br>
    <s:form>
        Title:<br>
        <s:textfield name="articleTitle" id="title"></s:textfield>
        <br>
        Content
        <br>
        <s:textarea name="articleContent" id="area"></s:textarea>
    </s:form>
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
</script>
</body>
</html>
