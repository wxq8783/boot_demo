package com.wu.pdm;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
public class ParsePDM {
//
//    private Element root;
//
//    public ParsePDM() throws DocumentException{
//        SAXReader reader = new SAXReader();
//        File pdm = new File("C:\\Users\\Administrator\\Desktop\\微贷网数仓模型设计.pdm");
//        Document doc = reader.read(pdm);
//        root = doc.getRootElement();
//    }
//    /**
//     * 加载所有domains
//     * @param args
//     */
////    public List<Domain> loadAllDomains(){
////        List<Domain> allDomains = new ArrayList<Domain>();
////        Element domainNode = (Element) root.selectSingleNode("//c:Domains");
////        if(domainNode == null){
////            return allDomains;
////        }
////        for(Object s:domainNode.elements()){
////            Element e = (Element) s;
////            Domain domain = new Domain();
////            String domainId = e.attributeValue("Id");
////            String domainName = e.elementText("Name");
////            domain.setDomainId(domainId);
////            domain.setDomainName(domainName);
////            allDomains.add(domain);
////        }
////
////        return allDomains;
////    }
//    /**
//     * 加载所有references,外键关系
//     * @param args
//     */
////    public List<Reference> loadAllReferences(){
////        List<Reference> allReferences = new ArrayList<Reference>();
////        Element refNode = (Element) root.selectSingleNode("//c:References");
////        if(refNode == null){
////            return allReferences;
////        }
////        for(Object s:refNode.elements()){
////            Element e = (Element) s;
////            Reference ref = new Reference();
////            String parentId = e.element("ParentTable").element("Table").attributeValue("Ref");
////            String FKId = e.element("Joins").element("ReferenceJoin").element("Object2").element("Column").attributeValue("Ref");
////            ref.setFKId(FKId);
////            ref.setParentId(parentId);
////            allReferences.add(ref);
////        }
////        return allReferences;
////    }
//    /**
//     * 加载所有主键Id
//     */
//    public List<String> loadAllPKIds(){
//        List<String> allPKIds = new ArrayList<String>();
//        Element tableNode = (Element) root.selectSingleNode("//c:Tables");
//        if(tableNode == null){
//            return allPKIds;
//        }
//        for(Object s:tableNode.elements()){
//            Element e = (Element) s;
//            //判断是否有主键
//            Element keys = e.element("Keys");
//            if(keys != null){
//                String PKId = keys.element("Key").element("Key.Columns").element("Column").attributeValue("Ref");
//                allPKIds.add(PKId);
//            }
//        }
//        return allPKIds;
//    }
//    /**
//     * 加载table的codeLib
//     */
//    public Map<String,String> loadTableCodeLib(){
//        Map<String,String> tableCodeLib = new HashMap<String, String>();
//        Element tableNode = (Element) root.selectSingleNode("//c:Tables");
//        for(Object s : tableNode.elements()){
//            Element t = (Element) s;
//            String id = t.attributeValue("Id");
//            String name = t.elementText("Name");
//            String code = t.elementText("Code");
//            tableCodeLib.put(id, name+code);
//        }
//        return tableCodeLib;
//    }
//    /**
//     * 加载所有的表
//     */
//    public List<Table> loadAllTables(){
//        List<Table> allTables = new ArrayList<Table>();
//        //主键
//        List<String> allPKIds = loadAllPKIds();
////        //外键
////        List<Reference> allReferences = loadAllReferences();
////        //作用域
////        List<Domain> allDomains = loadAllDomains();
//
//        Element tableNode = (Element) root.selectSingleNode("//c:Tables");
//        for(Object s:tableNode.elements()){
//            Element t = (Element) s;
//            Table table = new Table();
//            List<Column> allColumns = new ArrayList<Column>();
//            String tableId = t.attributeValue("Id");
//            String tableName = t.elementText("Name");
//            String tableCode = t.elementText("Code");
//            //1
//            table.setTableId(tableId);
//            //2
//            table.setTableName(tableName);
//            //3
//            table.setTableCode(tableCode);
//            //Column信息添加
//            Element columnNode = t.element("Columns");
//            for(Object ss:columnNode.elements()){
//                Element col = (Element) ss;
//                Column column = new Column();
//                String columnId = col.attributeValue("Id");
//                String columnName = col.elementText("Name");
//                String columnCode = col.elementText("Code");
//                String columnComment = col.elementText("Comment");
////                String domainId = col.element("Domain").element("PhysicalDomain").attributeValue("Ref");
////                String columnDomain = "";
////                //根据domainId获取domainName
////                for(Domain d : allDomains){
////                    if(domainId.equals(d.getDomainId())){
////                        columnDomain = d.getDomainName();
////                    }
////                }
//                boolean PK = false;
//                //获取主键
//                if(allPKIds.contains(columnId)){
//                    PK = true;
//                }
////                boolean FK = false;
////                //获取外键
////                for(Reference ref : allReferences){
////                    if(columnId.equals(ref.getFKId())){
////                        FK = true;
////                        //4
////                        table.setParentTableId(ref.getParentId());
////                    }
////                }
//                column.setColId(columnId);
//                column.setColName(columnName);
//                column.setColCode(columnCode);
//                column.setColComment(columnComment);
//                column.setColDomain(null);
//                column.setPK(PK);
//                allColumns.add(column);
//            }
//            //5
//            table.setAllColumns(allColumns);
//            allTables.add(table);
//        }
//        return allTables;
//    }
//    public void printAllTables(List<Table> allTables){
//        //表的id和name的表
//        Map<String,String> tableCodeLib = loadTableCodeLib();
//        for(Table table : allTables){
//            System.out.println("--------------------------------------------------------------------------");
//            System.out.println("表:"+table.getTableName()+table.getTableCode());
//            for(Entry<String, String> entry:tableCodeLib.entrySet()){
//                if(entry.getKey().equals(table.getParentTableId())){
//                    System.out.println("parentTable:"+entry.getValue());
//                }
//            }
//            String tablePK = "";
//            String tableFK = "";
//            for(Column column : table.getAllColumns()){
//                System.out.println("字段:"+column.getColCode()+","+"字段名称:"+column.getColName()+"注释:"+column.getColComment()+","+"domain:"+column.getColDomain());
//            }
//
//        }
//    }
//    public static void main(String[] args) {
//        try {
//            long start = System.currentTimeMillis();
//            ParsePDM parsePDM = new ParsePDM();
//            List<Table> allTables = parsePDM.loadAllTables();
//            long end = System.currentTimeMillis();
//            parsePDM.printAllTables(allTables);
//            System.out.println("用时:"+(end-start));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }
}
//
