package com.xiaoweii.train.generator.gen;


import com.xiaoweii.train.generator.util.DbUtil;
import com.xiaoweii.train.generator.util.Field;
import com.xiaoweii.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ServerGenerator {
    static boolean readOnly = true;
    static String vuePath = "admin/src/views/main/";
    static String serverPath = "[module]/src/main/java/com/xiaoweii/train/[module]/";
    static String pomPath = "train-generator/pom.xml";

    static {
//        new File(serverPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        //获取mybatis-generator
        String generatorPath = getGeneratorPath();
        //比如generator-config-member.xml, 得到module = member
        String module = generatorPath.replace("src/main/resources/generator-config-", "").replace(".xml", "");
        System.out.println("module: " + module);

        serverPath = serverPath.replace("[module]", module);
        //new File(servicePath).mkdirs();
        System.out.println("servicePath: " + serverPath);


        //读取table节点
        Document document = new SAXReader().read("train-generator/" + generatorPath);//拼接"train-generator/"模块路径再拼接当前路径
        Node table = document.selectSingleNode("//table");//两个斜杠表示寻找所有的table节点
        System.out.println(table);
        Node tableName = table.selectSingleNode("@tableName");//@表示查属性
        Node domainObjectName = table.selectSingleNode("@domainObjectName");
        System.out.println(tableName.getText() + "/" + domainObjectName.getText());

        //为DbUtil设置数据源
        Node connectionURL = document.selectSingleNode("//@connectionURL");
        Node userId = document.selectSingleNode("//@userId");
        Node password = document.selectSingleNode("//@password");
        System.out.println("url: " + connectionURL.getText());
        System.out.println("userId: " + userId.getText());
        System.out.println("password: " + password.getText());
        DbUtil.url = connectionURL.getText();
        DbUtil.user = userId.getText();
        DbUtil.password = password.getText();


        // 示例: 表名 xxx_test
        // Domain = XxxTest
        String Domain = domainObjectName.getText();
        // domain = xxxTest
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        // do_main = xxx-test //url命名规范: 全小写, 多个单词用 - 拼接
        String do_main = tableName.getText().replaceAll("_", "-");
        // 表中文名
        String tableNameCn = DbUtil.getTableComment(tableName.getText());
        List<Field> fieldList = DbUtil.getColumnByTableName(tableName.getText());
        // 获取类型Set集合
        Set<String> typeSet = getJavaTypes(fieldList);

        //组装参数
        Map<String, Object> param = new HashMap<>();
        param.put("module", module);
        param.put("Domain", Domain);
        param.put("domain", domain);
        param.put("do_main", do_main);
        param.put("tableNameCn", tableNameCn);
        param.put("fieldList", fieldList);
        param.put("typeSet", typeSet);
        param.put("readOnly", readOnly);
        System.out.println("组装参数: " + param);

//        gen(Domain, param, "service", "service");
//        gen(Domain, param, "controller/admin", "adminController");
//        gen(Domain, param, "req", "saveReq");
//        gen(Domain, param, "req", "queryReq");
//        gen(Domain, param, "resp", "queryResp");

        genVue(Domain, param);

    }

    private static void gen(String Domain, Map<String, Object> param, String packageName, String target) throws IOException, TemplateException {
        FreemarkerUtil.initConfig(target + ".ftl");
        String toPath = serverPath + packageName + "/";
        new File("train-"+toPath).mkdirs();
        String Target = target.substring(0, 1).toUpperCase() + target.substring(1);
        String fileName = "train-" + toPath + Domain + Target + ".java";
        System.out.println("开始生成: " + fileName);
        FreemarkerUtil.generator(fileName, param);
    }

    private static void genVue(String Domain, Map<String, Object> param) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("vue.ftl");
        new File(vuePath).mkdirs();
        String fileName = vuePath + Domain + "View.vue";
        System.out.println("开始生成: " + fileName);
        FreemarkerUtil.generator(fileName, param);
    }

    private static String getGeneratorPath() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Map<String, String> map = new HashMap<>();
        map.put("pom", "http://maven.apache.org/POM/4.0.0");
        saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document document = saxReader.read(pomPath);
        //从根目录下寻找,pom是xml命名空间, configurationFile是节点名, 如果要找属性,则是@xxx
        //使用XPath快速定位节点或属性;
        Node node = document.selectSingleNode("//pom:configurationFile");
        System.out.println(node.getText());
        return node.getText();
    }

    /*
     * 获取所有的Java类型, 使用Set去重
     * 目的是: import的包只引入一次, 不被多次引入, 就利用到了Set集合特性
     * */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getType());
        }
        return set;
    }

}
