# Selling

### 使用springboot的仿饿了么微信小程序的后端

- jpa持久层方案
- 添加springboottest
- 添加maven-mybatis代码生成插件
- 使用mybatis3
- 使用slf4j+logback
- 添加rest风格支持
- 添加jdk1.8新特性lombok
- 使用jdk1.8表达式lambda
- 支持订单校验
- 支持全局异常处理
- 微信网页授权
- 使用Charles抓包测试
- 使用freemarker网页静态化

### 启动
> 修改application.properties配置
- 直接main方法启动
- maven 启动

         mvn spring-boot:run -Dmaven.test.skip=true
         
- 打成jar包启动

        mvn clean package -Dmaven.test.skip=true 
      
然后进入jar文件目录，java -jar  

### 实现功能

#### 买家端

> 首页商品及商品分类展示
- 
        GET /sell/buyer/product/list


- 参数

        无


- 返回

        {
            "code": 0,
            "msg": "成功",
            "data": [
                {
                    "name": "热榜",
                    "type": 1,
                    "foods": [
                        {
                            "id": "123456",
                            "name": "皮蛋粥",
                            "price": 1.2,
                            "description": "好吃的皮蛋粥",
                            "icon": "http://xxx.com",
                        }
                    ]
                }
            ]
        }


> 创建订单
- 
        POST /sell/buyer/order/create

- 参数

        name: "张三"
        phone: "18868822111"
        address: "武汉XXX"
        openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
        items: [{
            productId: "1423113435324",
            productQuantity: 2 //购买数量
        }]

- 返回

        {
          "code": 0,
          "msg": "成功",
          "data": {
              "orderId": "147283992738221" 
          }
        }
        
> 订单列表

- 
        GET /sell/buyer/order/list


- 参数

        openid: 18eu2jwk2kse3r42e2e
        page: 0 //从第0页开始
        size: 10

- 返回

        {
          "code": 0,
          "msg": "成功",
          "data": [
            {
              "orderId": "161873371171128075",
              "buyerName": "张三",
              "buyerPhone": "18868877111",
              "buyerAddress": "武汉XXX",
              "buyerOpenid": "18eu2jwk2kse3r42e2e",
              "orderAmount": 0,
              "orderStatus": 0,
              "payStatus": 0,
              "createTime": 1490171219,
              "updateTime": 1490171219,
            }]
        }
        
> 查询订单详情

- 
        GET /sell/buyer/order/detail

- 参数
        
        openid: 18eu2jwk2kse3r42e2e
        orderId: 161899085773669363

- 返回

        {
            "code": 0,
            "msg": "成功",
            "data": {
                  "orderId": "161899085773669363",
                  "buyerName": "李四",
                  "buyerPhone": "18868877111",
                  "buyerAddress": "武汉XXX",
                  "buyerOpenid": "18eu2jwk2kse3r42e2e",
                  "orderAmount": 18,
                  "orderStatus": 0,
                  "payStatus": 0,
                  "createTime": 1490177352,
                  "updateTime": 1490177352,
                  "orderDetailList": [
                    {
                        "detailId": "161899085974995851",
                        "orderId": "161899085773669363",
                        "productId": "157875196362360019",
                        "productName": "招牌奶茶",
                        "productPrice": 9,
                        "productQuantity": 2,
                        "productIcon": "http://xxx.com",
                        "productImage": "http://xxx.com"
                    }
                ]
            }
        }
        
> 取消订单
- 
        POST /sell/buyer/order/cancel

- 参数

        openid: 18eu2jwk2kse3r42e2e
        orderId: 161899085773669363

- 返回

        {
            "code": 0,
            "msg": "成功",
            "data": null
        }
        
> 获取openid

- 
        重定向到 /sell/wechat/authorize


- 参数

        returnUrl: http://xxx.com/abc  //【必填】

- 返回

        http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg

> 支付订单

- 
        重定向到 /sell/pay/create


- 参数

        orderId: 161899085773669363
        returnUrl: http://xxx.com/abc/order/161899085773669363

- 返回

        http://xxx.com/abc/order/161899085773669363
        
> 微信支付异步通知

- 
        重定向到 /sell/pay/notify


- 参数

        微信传递过来的json对象

- 返回

        http://xxx.com/abc/sell/pay/success

#### 卖家网页端

> 首页订单列表


> 订单详情页

> 取消订单

> 完结订单

> 商品订单列表

> 商品上架/下架

> 商品新增/编辑页面

> 商品保存/更新




