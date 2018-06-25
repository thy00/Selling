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
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                            <#list orderDTOS.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.orderStatusEnum.message}</td>
                                <td>${orderDTO.payStatusEnum.message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                <td>
                                        <#if orderDTO.orderStatus==0>
                                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=10">上一页</a></li>
                        </#if>

                        <#list 1..orderDTOS.getTotalPages() as index>
                            <#if currentPage==index>
                            <li class="disabled"><a href="javascript:void(0)">${index}</a></li>
                            <#else>
                            <li><a href="/sell/seller/order/list?page=${index}&size=10">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte orderDTOS.getTotalPages()>
                            <li class="disabled"><a href="javascript:void(0)">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=10">上一页</a></li>
                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


