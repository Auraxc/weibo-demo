<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#--<#list todos as todo>-->
<#--    ${todo.id} : ${todo.content} : ${todo.user}-->
<#--</#list>-->

<#list todos as todo>
    ${todo.id} : ${todo.content}
</#list>
</body>
</html>