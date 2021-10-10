package cn.yunhe.test;

import cn.yunhe.bean.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFreemarker {

    //根据模板生成静态html页面，并输出到磁盘指定位置
    @Test
    public void testGenHtmlByTemplateFile() throws IOException, TemplateException {
        //处理模板
        //创建configuration配置类，说明模板的位置
        Configuration configuration = new Configuration(Configuration.getVersion());

        String path = this.getClass().getResource("/templates").getPath();
        //设置模板所在目录
       configuration.setDirectoryForTemplateLoading(new File(path));
       //设置模板编码
        configuration.setDefaultEncoding("utf-8");
        //获取模板对象
        Template template = configuration.getTemplate("test1.ftl");

        //获取模型数据
        Map modelData = this.getModelData();

        //通过freemarker引擎生成静态html内容
        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelData);

        //把生成的html内容输出到磁盘指定位置
        InputStream inputStream = IOUtils.toInputStream(htmlContent, "utf-8");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\clone\\stu.html"));
        IOUtils.copy(inputStream,fileOutputStream);

        inputStream.close();
        fileOutputStream.close();

    }

    @Test
    public void testGenHtmlByTemplateString() throws IOException, TemplateException {
        //处理模板
        //创建configuration配置类，说明模板的位置
        Configuration configuration = new Configuration(Configuration.getVersion());

        String stringTemplate = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf‐8\">\n" +
                "    <title>Hello World!</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<#--  这里获取值时如果为空，则会直接报错 -->\n" +
                "<#--  可以使用 ${name ! \"王琛\"} 的方式，如果要获取的内容为空，则默认为双引或者单引号内的值  -->\n" +
                "Hello ${name ! \"王琛\"}\n" +
                "\n" +
                "<#--  也可以这样 使用if先判断 不为空才获取 -->\n" +
                "<#--<#if name??>-->\n" +
                "    <#--${name}!-->\n" +
                "<#--</#if>-->\n" +
                "\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <td>序号</td><td>姓名</td><td>工资</td><td>生日</td>\n" +
                "    </tr>\n" +
                "    <#--  freemarker遍历集合  -->\n" +
                "    <#list stus as stu>\n" +
                "    <#--  ? string('dd.MM.yyyy HH:mm:ss')   也可以写成?date date为freemarker的内嵌函数   date日期 datetime日期时间 time时间-->\n" +
                "        <tr><td>${stu_index+1}</td><td>${stu.name}</td><td>${stu.money}</td><td>${stu.birthday?date}</td></tr>\n" +
                "    </#list>\n" +
                "    <#--  拿到list集合的方式  -->\n" +
                "    <tr><td>1</td><td>${stu1.name}</td><td>${stu1.money}</td><td>${stu1.age}</td></tr>\n" +
                "    <#--  拿到map中元素的两种方式  -->\n" +
                "    <tr><td>2</td><td>${stuMap.stu1.name}</td><td>${stuMap.stu1.money}</td><td>${stuMap.stu1.age}</td></tr>\n" +
                "    <tr><td>3</td><td>${stuMap[\"stu2\"].name}</td><td>${stuMap[\"stu2\"].money}</td><td>${stuMap[\"stu2\"].age}</td></tr>\n" +
                "\n" +
                "    <#list stuMap? keys as key >\n" +
                "        <#--                             这样写是错误的，这里只有知道具体的key，才可以stuMap.stu1.name ... -->\n" +
                "        <#--<tr><td>${key_index+1}</td><td>${stuMap.key.name}</td><td>${stu.money}</td><td>${stu.age}</td></tr>-->\n" +
                "        <#--  这里只能这样写  -->\n" +
                "        <tr <#if key_index % 2 == 0>bgcolor=\"yellow\" </#if>><td>${key_index+1}</td><td>${stuMap[key].name}</td><td>${stuMap[key].money}</td><td>${stuMap[key].age}</td></tr>\n" +
                "    </#list>\n" +
                "</table>\n" +
                "    <#assign text=\"{'bank':'工商银行','account':'10101920201920212'}\" />\n" +
                "    <#assign data=text?eval />\n" +
                "    开户行：${data.bank}  账号：${data.account}\n" +
                "    ${point?c}\n" +
                "</body>\n" +
                "</html>";
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",stringTemplate);
        configuration.setTemplateLoader(stringTemplateLoader);

        //获取模板
        Template template = configuration.getTemplate("template");

        //获取模型数据
        Map modelData = this.getModelData();
        //通过freemarker引擎生成静态html内容
        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelData);

        //把生成的html内容输出到磁盘指定位置
        InputStream inputStream = IOUtils.toInputStream(htmlContent, "utf-8");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\clone\\stu1.html"));
        IOUtils.copy(inputStream,fileOutputStream);

        inputStream.close();
        fileOutputStream.close();

    }
    private Map getModelData(){
        Map map = new HashMap();

        //向数据模型放数据
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
//        model.addAttribute("stus",stus);
        map.put("stus",stus);

        //准备map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        //向数据模型放数据
        map.put("stu1",stu1);
        map.put("stu2",stu2);
        //向数据模型放数据
        map.put("stuMap",stuMap);

        map.put("point", 102920122);

        return map;
    }
}
