<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>留言板</title>
    </head>
    <body>

        <h3>很好 h3 版</h3>
        <form action="/message/add" method="get">
            <textarea name="content" rows="8" cols="40"></textarea>
            <br/>
            <button type="submit">GET 提交</button>
        </form>

        <#list messages as message>
            <h3>
                ${message.content}, ${message.userId}
            </h3>

        </#list>
    </body>
</html>
