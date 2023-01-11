<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<s:form
        action="uploadfile"
        method="post"
        enctype="multipart/form-data">

    <s:file name="strutsUploadedFile" label="File" />
    <s:hidden name="strutsAction" value="upload"></s:hidden>
    <s:submit />

</s:form>
</body>
</html>
