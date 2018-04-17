package controller;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.mongodb;
import domain.AnjuanjiItem;
import domain.ElasticTextContent;
import domain.WenjianjiItem;
import org.bson.Document;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dao.mongodb.DATABASE;
import static dao.mongodb.MONGOIP;

@Controller
public class PudongListController {
    static TransportClient transportClient;
    static final String CLUSTER_NAME="my-application";
    static final String CLUSTER_IP="10.131.247.202";
    static final int PORT=9300;
    @RequestMapping(value = "/controller/lookfortext")
    public String searchElasticResult1(Map<String,Object> model) throws Exception {
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        List<ElasticTextContent> itemsList=null;
        return "items/searchview";
    }


    @RequestMapping(value = "/controller/searchall.action")
    public  String searchAllFromTable(Map<String,Object> model) throws Exception {
        return "items/mergesearch";
    }

    @RequestMapping(value = "/controller/displaytextandmongocontent.action")
    public ModelAndView  displayalltablesResult(@RequestParam("content") String content,@RequestParam("search") String searchtype)
    {
        ArrayList<ElasticTextContent> itemlist;

        System.out.println(searchtype);
        System.out.println(content);
        if(searchtype.equals("mongodb")) {
            return getMongodbdata(content);
        }else
        {
            ModelAndView modelAndView = new ModelAndView();
            itemlist=searchElasticTextContentByIK("content",content);
            System.out.println(itemlist.size());
            modelAndView.addObject("itemsList", itemlist);
            //指定视图
            modelAndView.setViewName("/items/textitemlist");
            return modelAndView;
        }

    }


    @RequestMapping(value = "/controller/linktomongodb/{id}.action", method = RequestMethod.GET)
    public ModelAndView searchAllFromTable(@PathVariable(value="id") String id) throws Exception {
        System.out.println("the id is"+id);
        ModelAndView modelAndView = new ModelAndView();
        //指定视图
        System.out.println("is Accurate");
        List<AnjuanjiItem> itemaccurateList=null;
        MongoDatabase mongoDb= mongodb.connectToMongodb(MONGOIP,DATABASE);
        if(mongoDb!=null)
        {
            MongoCollection<Document> usercollection=mongoDb.getCollection("anjuanji");
            //listAllDocuments(usercollection);
            //"OWNER"  "丁炯"
            itemaccurateList= mongodb.finditem(usercollection,"AID",id);
            // System.out.println(itemaccurateList.size());
        }else {
            System.err.println("IP address error");
        }

        modelAndView.addObject("itemsList", itemaccurateList);
        modelAndView.setViewName("items/itemslist");
        return modelAndView;
    }



    /**
     * 通过es获取案卷级和文件级的数据
     * @param content
     * @return
     */
    private ModelAndView getMongodbdata(String content) {
        //获取案卷级
        ModelAndView modelAndView = new ModelAndView();
        List<AnjuanjiItem> listAnjuanji = searchElasticContentFromAnjuanjiTable(content);
        modelAndView.addObject("itemsListAnjuanji", listAnjuanji);
        //获取文件级
        List<WenjianjiItem> wenjianji=searchElasticContentFromWenjianjiTable(content);
        modelAndView.addObject("itemsListwenjianji", wenjianji);
        //指定视图
        modelAndView.setViewName("items/itemslistfromtwotable");
        return modelAndView;
    }


    private ArrayList<AnjuanjiItem> searchElasticContentFromAnjuanjiTable(String content) {
        Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME)//指定集群名称
                .put("network.host", CLUSTER_IP)
                .put("client.transport.ping_timeout", "5s")
                .put("client.transport.sniff", true)
                .put("client.transport.ignore_cluster_name", true)//启动嗅探功能
                .build();
        //.addTransportAddresses(new InetSocketAddress(InetAddress.getByName(10.141.211.142,9200)));
        transportClient= new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress( CLUSTER_IP,9300)));
        SearchRequestBuilder responsebuilder = transportClient.prepareSearch("pudong").setTypes("anjuanji");
        // SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.matchQuery("OTHEROWNER","方洁毕丞")).setFrom(0).setSize(20).setExplain(true).execute().actionGet();
        //案卷级
        ArrayList itemsList=new ArrayList<AnjuanjiItem>();
        SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.simpleQueryStringQuery(content )).setFrom(0).setSize(20).setExplain(true).execute().actionGet();
        SearchHits hits = myresponse.getHits();
        AnjuanjiItem item;
        for (int i = 0; i < hits.getHits().length; i++) {
            System.out.println(i+"-"+hits.getHits()[i].getSourceAsString());
            Map<String,Object>  mapitem=hits.getHits()[i].getSource();
           /* System.out.println( mapitem.get("OWNER"));
            System.out.println(mapitem.get("OTHEROWNER"));
            System.out.println( mapitem.get("OLDOWNER"));
            System.out.println( mapitem.get("HOUSEPLACE"));*/
            item=new AnjuanjiItem();
            item.setPIGEONHOLEDATE((String) mapitem.get("PIGEONHOLEDATE"));
            item.setHOUSEPLACE( (String)mapitem.get("HOUSEPLACE"));
            item.setOLDOWNER((String) mapitem.get("OLDOWNER"));
            item.setOTHEROWNER((String)mapitem.get("OTHEROWNER"));
            item.setOWNER((String)mapitem.get("OWNER"));
            item.setGATHERDATE((String)mapitem.get("GATHERDATE"));
            itemsList.add(item);
        }
        System.out.println("success connect");
        return itemsList;
    }


    private ArrayList<WenjianjiItem> searchElasticContentFromWenjianjiTable(String content) {
        Settings settings = Settings.builder()
                .put("cluster.name", CLUSTER_NAME)//指定集群名称
                .put("network.host", CLUSTER_IP)
                .put("client.transport.ping_timeout", "5s")
                .put("client.transport.ignore_cluster_name", true)//启动嗅探功能
                .build();
        //.addTransportAddresses(new InetSocketAddress(InetAddress.getByName(10.141.211.142,9200)));
        transportClient= new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress( CLUSTER_IP,9300)));
        SearchRequestBuilder responsebuilder1 = transportClient.prepareSearch("pudong").setTypes("wenjianji");
        // SearchResponse myresponse=responsebuilder.setQuery(QueryBuilders.matchQuery("OTHEROWNER","方洁毕丞")).setFrom(0).setSize(20).setExplain(true).execute().actionGet();
        SearchResponse myresponse2=responsebuilder1.setQuery(QueryBuilders.simpleQueryStringQuery(content)).setFrom(0).setSize(10).setExplain(true).execute().actionGet();
        SearchHits hits2 = myresponse2.getHits();
        //文件级
        ArrayList itemsList=new ArrayList<AnjuanjiItem>();
        WenjianjiItem item;
        for (int i = 0; i < hits2.getHits().length; i++) {
            Map<String,Object>  mapitem=hits2.getHits()[i].getSource();
            System.out.println(i+"-"+hits2.getHits()[i].getSourceAsString());
            item=new WenjianjiItem();
            item.setCdPath((String) mapitem.get("CDPATH"));
            item.setFileCode((String) mapitem.get("FILECODE"));
            item.setFilePath((String) mapitem.get("FILEPATH"));
            if( mapitem.get("PERSON")==null)
            {
                item.setPerson("");
            }else{
                item.setPerson((String) mapitem.get("PERSON"));
            }
            item.setTitle((String) mapitem.get("TITLE"));
            itemsList.add(item);
        }
        System.out.println("success connectwenjianji");
        return itemsList;
    }

    /**
     * 通过IK索引文本
     * @param type
     * @param keyword
     * @return
     */
    public static ArrayList searchElasticTextContentByIK( String type ,String keyword ){
        Settings settings = Settings.builder()
                .put("cluster.name", CLUSTER_NAME)//指定集群名称
                .put("network.host", CLUSTER_IP)
                .put("client.transport.ping_timeout", "5s")
                .put("client.transport.ignore_cluster_name", true)//启动嗅探功能
                .build();
        MultiMatchQueryBuilder query = QueryBuilders.multiMatchQuery(keyword, type);
        TransportClient transportClient=new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(CLUSTER_IP,9300)));
        //检索设置
        SearchResponse response = transportClient.prepareSearch("myocrdata")
                .setTypes("pudongiksmart")
                .setQuery(query)
                .setFrom(0)
                .setSize(5)   //从0开始，默认推荐5个
                .execute()
                .actionGet();
        //执行检索
        SearchHits hits = response.getHits();
        //以json数组存储搜索结果
        ArrayList itemsList=new ArrayList<ElasticTextContent>();
        ElasticTextContent item;
        System.out.println("共搜到："+hits.getTotalHits()+"条结果");
        int i=0;
        for (SearchHit hit : hits)
        {
            item=new ElasticTextContent();
            System.out.println("item"+i+hit.getSource().get("content"));
            System.out.println("item"+i+hit.getSource().get("mongo_address"));
            item.setContent((String) hit.getSource().get("content"));
            item.setMongo_adress((String)hit.getSource().get("mongo_address"));
            itemsList.add(item);
        }
        return itemsList;
    }


}
