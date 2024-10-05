package com.xiaoweii.train.generator.server;


import com.xiaoweii.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {

    static String toPath = "train-generator/src/main/java/com/xiaoweii/train/generator/test/";
    static String pomPath = "train-generator/pom.xml";

    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        String generatorPath = getGeneratorPath();

        Document document = new SAXReader().read("train-generator/" + generatorPath);//拼接"train-generator/"模块路径再拼接当前路径
        Node table = document.selectSingleNode("//table");//两个斜杠表示寻找所有的table节点
        System.out.println(table);
        Node tableName = table.selectSingleNode("@tableName");//@表示查属性
        Node domainObjectName = table.selectSingleNode("@domainObjectName");
        System.out.println(tableName.getText() + "/" + domainObjectName.getText());


//        FreemarkerUtil.initConfig("test.ftl");
//        HashMap<String, Object> param = new HashMap<>();
//        param.put("domain", "Test");
//        FreemarkerUtil.generator(toPath + "Test.java", param);
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

}
