<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
<#--  这里获取值时如果为空，则会直接报错 -->
<#--  可以使用 ${name ! "王琛"} 的方式，如果要获取的内容为空，则默认为双引或者单引号内的值  -->
Hello ${name ! "王琛"}

<#--  也可以这样 使用if先判断 不为空才获取 -->
<#--<#if name??>-->
    <#--${name}!-->
<#--</#if>-->

<table>
    <tr>
        <td>序号</td><td>姓名</td><td>工资</td><td>生日</td>
    </tr>
    <#--  freemarker遍历集合  -->
    <#list stus as stu>
    <#--  ? string('dd.MM.yyyy HH:mm:ss')   也可以写成?date date为freemarker的内嵌函数   date日期 datetime日期时间 time时间-->
        <tr><td>${stu_index+1}</td><td>${stu.name}</td><td>${stu.money}</td><td>${stu.birthday?date}</td></tr>
    </#list>
    <#--  拿到list集合的方式  -->
    <tr><td>1</td><td>${stu1.name}</td><td>${stu1.money}</td><td>${stu1.age}</td></tr>
    <#--  拿到map中元素的两种方式  -->
    <tr><td>2</td><td>${stuMap.stu1.name}</td><td>${stuMap.stu1.money}</td><td>${stuMap.stu1.age}</td></tr>
    <tr><td>3</td><td>${stuMap["stu2"].name}</td><td>${stuMap["stu2"].money}</td><td>${stuMap["stu2"].age}</td></tr>

    <#list stuMap? keys as key >
        <#--                             这样写是错误的，这里只有知道具体的key，才可以stuMap.stu1.name ... -->
        <#--<tr><td>${key_index+1}</td><td>${stuMap.key.name}</td><td>${stu.money}</td><td>${stu.age}</td></tr>-->
        <#--  这里只能这样写  -->
        <tr <#if key_index % 2 == 0>bgcolor="yellow" </#if>><td>${key_index+1}</td><td>${stuMap[key].name}</td><td>${stuMap[key].money}</td><td>${stuMap[key].age}</td></tr>
    </#list>
</table>
    <#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
    <#assign data=text?eval />
    开户行：${data.bank}  账号：${data.account}
    ${point?c}
</body>
</html>