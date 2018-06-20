# Selling

#### 使用springboot的仿饿了么微信小程序的后端

- jpa持久层方案
- 添加springboottest
- 添加maven-mybatis代码生成插件
- 使用mybatis3
- 使用slf4j+logback
- 添加rest风格支持
- 添加jdk1.8新特性lombok

##### 启动
> 修改application.properties配置
- 直接main方法启动
- maven 启动

         mvn spring-boot:run -Dmaven.test.skip=true
         
- 打成jar包启动

        mvn clean package -Dmaven.test.skip=true 
      
然后进入jar文件目录，java -jar  
