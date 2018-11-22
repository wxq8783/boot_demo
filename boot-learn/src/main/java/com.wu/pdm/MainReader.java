package com.wu.pdm;

import com.alibaba.fastjson.JSON;
import org.dom4j.*;
import org.dom4j.io.SAXReader;;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;


/**
 * @author chenjazz
 */
public class MainReader {
    static Namespace oNamespace = new Namespace("o", "object");
    static Namespace cNamespace = new Namespace("c", "collection");
    static Namespace aNamespace = new Namespace("a", "attribute");
    static int i = 0;
    public static void main(String[] args) throws DocumentException {
        String fileName = "C:\\Users\\Administrator\\Desktop\\微贷网数仓模型设计.pdm";
        System.out.println(fileName);
        long start = System.currentTimeMillis();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(fileName));
        Element rootElement = document.getRootElement();
        Element rootObject = rootElement.element(new QName("RootObject", oNamespace));
        Element children = rootObject.element(new QName("Children", cNamespace));
        Element model = children.element(new QName("Model", oNamespace));
        //解析package
        Element packagesEle = model.element(new QName("Packages", cNamespace));
        Map<String,PDMTable> allTableMap = new LinkedHashMap<>();
        if (packagesEle != null) {
            allTableMap = parsePackages(packagesEle , allTableMap);
        }
        if(!CollectionUtils.isEmpty(allTableMap)){
            Iterator<Map.Entry<String,PDMTable>> it = allTableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()){
                PDMTable pdmTable = it.next().getValue();
                System.out.println("-------------第"+ i++ +"个------------------------------");
                System.out.println(pdmTable.getFirstTopic()+"-"+pdmTable.getSecondTopic()+"-->"+pdmTable.getCode());
                //System.out.println(JSON.toJSONString(pdmTable));
                //System.out.println("-------------------------------------------");
            }
        }


    }

    static Map<String,PDMTable> parsePackages(Element packagesEle ,Map<String,PDMTable> tableMap){
        List<Element> packageEles = packagesEle.elements(new QName("Package", oNamespace));
        for (Element packageEle : packageEles) {
            //Map<String,PDMTable> tableMap = new HashMap<>();
            Element secondPackagesEls = packageEle.element(new QName("Packages", cNamespace));
            if(secondPackagesEls != null){
                parsePackages(secondPackagesEls , tableMap);
            }
            String firstTopic = packageEle.element(new QName("Name", aNamespace)).getText();
            //获取二级主题
            List<TopicInfo> topicInfoList = new ArrayList<>();
            Element physicalDiagrams = packageEle.element(new QName("PhysicalDiagrams",cNamespace));
            if(physicalDiagrams != null){
                List<Element> pdList =  physicalDiagrams.elements(new QName("PhysicalDiagram", oNamespace));
                for(Element pd :  pdList){
                    String secondTopic = pd.element(new QName("Name", aNamespace)).getText();
                    //获取二级主题关联的表
                    Element symbols = pd.element(new QName("Symbols",cNamespace));
                    if(symbols != null){
                        List<Element> symbolList = symbols.elements(new QName("TableSymbol", oNamespace));
                        for(Element symbol : symbolList){
                            Element tabRefs = symbol.element(new QName("Object",cNamespace));
                            if(tabRefs != null){
                                List<Element> tableObject = tabRefs.elements(new QName("Table",oNamespace));
                                if(!CollectionUtils.isEmpty(tableObject)){
                                    Element tableRef = tableObject.get(0);
                                    String tableId = tableRef.attribute("Ref").getValue();
                                    if(tableId.equals("o2492")){
                                        System.out.println("------");
                                    }
                                    TopicInfo topicInfo = new TopicInfo(firstTopic,secondTopic,tableId);
                                    topicInfoList.add(topicInfo);
                                }
                            }
                        }
                    }
                }
            }

            Element tablesEle = packageEle.element(new QName("Tables", cNamespace));

            if (tablesEle != null) {
                List<Element> tabElementList = tablesEle.elements(new QName("Table", oNamespace));
                for(Element tabElement : tabElementList ){
                    parseTable(tabElement , tableMap);
                }
            }
            if(!CollectionUtils.isEmpty(topicInfoList) && !CollectionUtils.isEmpty(tableMap)){
                for(TopicInfo topicInfo : topicInfoList){
                    PDMTable pdmTable = tableMap.get(topicInfo.getTableId().trim());
                    pdmTable.setFirstTopic(topicInfo.getFirstTopic());
                    pdmTable.setSecondTopic(topicInfo.getSecondTopic());
                }
            }
            tableMap.putAll(tableMap);
        }
        return tableMap;
    }


    public static void parseTable(Element tableElement , Map<String,PDMTable> tableMap){

        PDMTable pdmTable = new PDMTable();
        Element name = tableElement.element(new QName("Name", aNamespace));
        Element code = tableElement.element(new QName("Code", aNamespace));
        String tabId = tableElement.attribute("Id").getValue();
        pdmTable.setCode(getTextFromEle(code));
        pdmTable.setName(getTextFromEle(name));
        pdmTable.setId(tabId);
        if(pdmTable.getCode().equals("dmb_opra_red_stats_dap")){
            System.out.println("----------");
        }
        //解析主键
        List<String> keyIds = parseKey(tableElement);

        List<PDMColumn> columnList = new ArrayList<>();
        //解析column
        List<Element> columns = tableElement.element(new QName("Columns", cNamespace)) != null ? tableElement.element(new QName("Columns", cNamespace)).elements(new QName("Column", oNamespace)):null;
        if(!CollectionUtils.isEmpty(columns)){
            for (Element columnEle : columns) {
                String columnId = columnEle.attribute("Id").getValue();
                Element cname = columnEle.element(new QName("Name", aNamespace));
                Element ccode = columnEle.element(new QName("Code", aNamespace));
                Element cDataType = columnEle.element(new QName("DataType", aNamespace));
                Element cLength = columnEle.element(new QName("Length", aNamespace));
                Element precision = columnEle.element(new QName("Precision", aNamespace));
                Element mandatory = columnEle.element(new QName("Mandatory", aNamespace));
                Element defaultValue = columnEle.element(new QName("DefaultValue", aNamespace));
                Element lowValue = columnEle.element(new QName("LowValue", aNamespace));
                Element highValue = columnEle.element(new QName("HighValue", aNamespace));
                Element cComment = columnEle.element(new QName("Comment", aNamespace));

                com.wu.pdm.PDMColumn pdmColumn  = new com.wu.pdm.PDMColumn();
                pdmColumn.setId(columnId);
                pdmColumn.setName(getTextFromEle(cname));
                pdmColumn.setCode(getTextFromEle(ccode));;
                pdmColumn.setDataType(getTextFromEle(cDataType));
                pdmColumn.setLength(getIntergeTextFromEle(cLength));
                pdmColumn.setPrecision(getIntergeTextFromEle(precision));
                pdmColumn.setMandatory(getIntergeTextFromEle(mandatory));
                pdmColumn.setDefaultValue(getTextFromEle(defaultValue));
                pdmColumn.setLowValue(getTextFromEle(lowValue));
                pdmColumn.setHighValue(getTextFromEle(highValue));
                pdmColumn.setComment(getTextFromEle(cComment));
                columnList.add(pdmColumn);

            }
        }
        pdmTable.setColumns(columnList);
        tableMap.put(tabId.trim(),pdmTable);
    }


    public static List<String> parseKey(Element tableElement){
        List<String> pkIds = new ArrayList<>();
        Element primaryKeyEle = tableElement.element(new QName("PrimaryKey", cNamespace));
        if (primaryKeyEle != null) {
            List<Element> pks = primaryKeyEle.elements(new QName("Key", oNamespace));
            for (Element pk1 : pks) {
                pkIds.add(pk1.attribute("Ref").getValue());
            }
        }

        Element keysEle = tableElement.element(new QName("Keys", cNamespace));
        List<String> pkColumnIds = new ArrayList<>();
        if (keysEle != null) {
            List<Element> keyEleList = keysEle.elements(new QName("Key", oNamespace));
            for (Element keyEle : keyEleList) {
                Attribute id = keyEle.attribute("Id");
                if (pkIds.contains(id.getValue())) {
                    List<Element> list = keyEle.element(new QName("Key.Columns", cNamespace)).elements(new QName("Column", oNamespace));
                    for (Element element : list) {
                        pkColumnIds.add(element.attribute("Ref").getValue());
                    }
                }
            }
        }
        return pkIds;
    }

    static String getTextFromEle(Element element) {
        if (element == null) {
            return "";
        }
        return element.getText().trim();
    }

    static Integer getIntergeTextFromEle(Element element) {
        if (element == null) {
            return null;
        }

        return Integer.parseInt(element.getText());

    }
    /**
     * @see String#format(String, Object...)
     */
    static String getPadString(String str, int length) {
        int size = str.length();
        if (size < length) {
            str += getBlank(length - size);
            return str;
        } else
            return str + "  ";
    }


    static String getBlank(int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            s.append(" ");
        }
        return s.toString();
    }
}