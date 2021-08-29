<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>上传文件</title>
</head>
<body>
${name}
<img src="/avator?file=${avator}" alt="">
<form method="post" action="/avator/add" enctype="multipart/form-data">
    <input type="file" name="file">
    <button>上传</button>
</form>

</body>
</html>