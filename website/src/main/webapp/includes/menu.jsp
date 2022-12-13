<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="tr-menubar">
    <div class="tr-menu-center">
        <a class="tr-menu-item" href="index">Home</a>
        <a class="tr-menu-item" href="neolan">Neolan</a>
        <a class="tr-menu-item" href="mcplugin">minecraft</a>
        <a class="tr-menu-item" href="https://triedge.ovh/todo">Todo</a>
        <a class="tr-menu-item" href="https://triedge.ovh/Amadeus">Amadeus</a>
        <a class="tr-menu-item" href="admin">Admin</a>
    </div>
</div>
<s:if test="#session['tuser'] != null">
    <div class="tr-session-user">
        <img src="<s:property value="#session['tuser'].image"></s:property>"/>
        <br>
        <div class="tr-session-menu">
            <div class="tr-session-content">
                <a href="profile">Profile</a>
                <a href="diconnect">Disconnect</a>
            </div>

        </div>
    </div>
</s:if>