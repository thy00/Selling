<html>
    <head>
        <meta charset="UTF-8">
        <title>商品信息错误</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            错误!
                        </h4> <strong>${msg}</strong> <a href="${url}" class="alert-link">3秒后自动跳转</a>
                    </div>
                </div>
            </div>
        </div>
    </body>

<script>
    setTimeout('location.href="${url}"',3000);
</script>
</html>