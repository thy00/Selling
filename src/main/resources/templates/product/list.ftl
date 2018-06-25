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
                                    <th>商品id</th>
                                    <th>名称</th>
                                    <th>图片</th>
                                    <th>单价</th>
                                    <th>库存</th>
                                    <th>描述</th>
                                    <th>类目</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                            <#list productInfos.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img height="100px" width="100px" src="${productInfo.productIcon}"></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>
                                        <#if productInfo.productStatus==0>
                                            <a href="/sell/seller/product/off_sale?productId=${(productInfo.productId)!""}">下架</a>
                                        <#elseif productInfo.productStatus==1>
                                            <a href="/sell/seller/product/on_sale?productId=${(productInfo.productId)!""}">上架</a>
                                        </#if>
                                </td>
                            </tr>
                            </#list>

                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 column">
                            <ul class="pagination  pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="javascript:void(0)">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage-1}&size=10">上一页</a></li>
                        </#if>

                        <#list 1..productInfos.getTotalPages() as index>
                            <#if currentPage==index>
                            <li class="disabled"><a href="javascript:void(0)">${index}</a></li>
                            <#else>
                            <li><a href="/sell/seller/product/list?page=${index}&size=10">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte productInfos.getTotalPages()>
                            <li class="disabled"><a href="javascript:void(0)">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=10">上一页</a></li>
                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


