<html>
    <head>
        <meta charset="UTF-8">
        <title>订单列表</title>
        <#include "../common/header.ftl">
    </head>
    <body>

        <div id="wrapper" class="toggled">
            <#--左边栏菜单-->
            <#include "../common/nav.ftl">
            <#--内容-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>类目id</th>
                                    <th>名称</th>
                                    <th>类型</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                            <#list categories as category>
                            <tr>
                                <td>${category.categoryId}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.categoryType}</td>
                                <td>${category.createTime}</td>
                                <td>${category.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                            </tr>
                            </#list>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


