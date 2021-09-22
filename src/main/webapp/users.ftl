<html lang="en">
<#include "base.ftl">
<#macro title>Users</#macro>

<#macro content>
    Hello,
    <#list users as u>
        ${u}
    </#list>!
</#macro>
</html>