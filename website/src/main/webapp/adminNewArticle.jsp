<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Triedge - New Article</title>
    <head>
        <s:include value="includes/header.jsp"/>
        <script src="includes/js/bbcode.js"></script>
        <script src="includes/js/editor.js"></script>
    </head>
</head>
<body>
<s:include value="includes/menu.jsp"/>
<div class="tr-hidden-separator"></div>
<div class="tr-content tr-padding">
    <ul class="tr-breadcrumb">
        <li><a href="admin">Admin</a></li>
        <li>Article</li>
    </ul>
    <h1>New Article</h1>
    <span class="tr-button" id="btnDraft">Save Draft</span>
    <span class="tr-button" id="btnPreview">Preview</span>
    <span class="tr-button" id="btnPublish">Publish</span>
    <br>
    <br>
    <div class="tr-article-toolbar">
        <span onclick="insertBBCode('t1','area');">T1</span>
        <span onclick="insertBBCode('t2','area');">T2</span>
        <span onclick="insertBBCode('b','area');">B</span>
        <span onclick="insertBBCode('i','area');">I</span>
        <span onclick="insertBBCode('u','area');">U</span>
        <span onclick="insertBBCode('code','area');">CODE</span>
        <span onclick="insertBBCodeUnique('sep','area');">SEP</span>
        <span onclick="insertBBCodeExtra('img=','img','area');">IMG</span>
        <span onclick="insertBBCodeExtra('url=','url','area');">URL</span>
        <span onclick="generateHTML();">Generate</span>
    </div>
    <table style="width: 100%">
        <tr>
            <td>
                Title:<br>
                <input type="text" class="w100" name="strutsArticleTitle" id="title" value="<s:property value="%{currentDraft.title}"></s:property>">
            </td>
        </tr>
        <tr>
            <td>
                Category:<br>
                <select name="strutsArticleCategory" id="category">
                    <s:iterator var="i" step="1" value="categories">
                        <s:if test="%{currentDraft.category.id==id}">
                            <option value="<s:property value="id"></s:property>" selected><s:property value="name"></s:property></option>
                        </s:if>
                        <s:else>
                            <option value="<s:property value="id"></s:property>"><s:property value="name"></s:property></option>
                        </s:else>
                    </s:iterator>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Image:<br>
                <input type="text" class="w100" name="strutsArticleThumbnail" id="image" value="<s:property value="%{currentDraft.thumbnail}"></s:property>"><br>
                <span class="tr-link" id="btnAvailableImages">Available Images</span><br>
                <div class="tr-image-select-box" id="resultImages"></div>
            </td>
        </tr>
        <tr>
            <td>
                Content:<br>
                <textarea class="w100 h500" name="strutsArticleContent" id="area"><s:property value="%{currentDraft.content}"></s:property></textarea>
            </td>
        </tr>
        <tr>
            <td>
                Description:<br>
                <textarea class="w100 h200" name="strutsArticleDesc" id="desc"><s:property value="%{currentDraft.description}"></s:property></textarea>
            </td>
        </tr>
    </table>
    <input type="hidden" value="<s:property value="%{currentDraft.id}"></s:property>" name="strutsArticleId" id="articleId">
    <div style="width: 100%" id="result"></div>
</div>
<script>
    $("#area").bind('input propertychange', function() {
        $("#btnDraft").html("Save Draft*");
    });

    $("#btnDraft").click(function(){
        ajaxSave(false);
    });

    $("#btnPublish").click(function(){
        ajaxSave(true);
    });

    $("#btnPreview").click(function(){
        window.open("article?strutsArticleId=<s:property value="%{currentDraft.id}"></s:property>", '_blank').focus();
    });

    $("#btnAvailableImages").click(function () {
        if ($("#resultImages").is(':empty')) {
            //await sleep(2000);
            ajaxLoadImages();
        } else {
            $("#resultImages").empty();
        }

    });

    function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function selectImage(img){
        $("#image").val(img);
    }

    function ajaxLoadImages(){
        $.ajax({
            url: 'ajaxlistimages',
            method: 'get',
            data: {
                strutsAction: 'list'
            },
            success: function(response){
                $("#resultImages").empty();
                $("#resultImages").html(response);
            }
        });
    }

    function ajaxSave(publish){
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
                strutsArticleTitle: tt,
                strutsArticleContent: $("#area").val(),
                strutsArticleId: $("#articleId").val(),
                strutsArticleThumbnail: $("#image").val(),
                strutsArticleDesc: $("#desc").val(),
                strutsArticleCategory: $("#category").val(),
                strutsPublish: publish
            },
            success: function(response){
                if (publish===true){
                    notify("Article published!");
                }else{
                    notify("Draft saved!");
                }
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
