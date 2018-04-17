package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import domain.AnjuanjiItem;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class mongodb {
    public static final String  MONGOIP= "10.141.211.145";
    public static final String  DATABASE= "pudong";
    public static MongoDatabase connectToMongodb(String ip, String db) {
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( ip , 27017 );
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
            System.out.println("Connect to database successfully");
            return  mongoDatabase;
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return null;
    }


    public static void main( String args[] ){
        //
        MongoDatabase mongoDb=connectToMongodb(MONGOIP,DATABASE);
        if(mongoDb!=null)
        {
            MongoCollection<Document> usercollection=mongoDb.getCollection("anjuanji");
            //listAllDocuments(usercollection);
            finditem(usercollection,"OWNER","丁炯");
        }else {
            System.err.println("IP address error");
        }

    }



    public static void listAllDocuments(MongoCollection<Document> collection) {
        System.out.println("begin get all document >>>>>>");
        for (Document document : collection.find()) {
            System.out.println(document);
        }
        System.out.println("finish get all document >>>>>>");
    }

    public static ArrayList<AnjuanjiItem> finditem(MongoCollection<Document> doc, String item , String para)
    {
        FindIterable<Document> iter;
       final ArrayList<AnjuanjiItem>  array=new ArrayList<AnjuanjiItem>();
        //FindIterable<Document> iter = doc.find(new Document(item,new Document("$eq",param)));
        if(item.equals("_id"))
        {
            System.out.println("查询id");
                BasicDBObject filter=new BasicDBObject();
                filter.put("_id", new ObjectId(para));
             List<Document> results = new ArrayList<Document>();
             iter= doc.find((Bson) filter);
            /*FindIterable<Document> iterables = doc.find((Bson) filter);
            MongoCursor<Document> cursor = iterables.iterator();
            while (cursor.hasNext()) {
                results.add(cursor.next());
            }
            for(Document doc1 : results){
                System.out.println("方法1：" + doc1.toJson());
                // 方法1：{ "_id" : { "$oid" : "59c8cbea6157d6c78dab4ff0" }, "name" : "yy", "password" : "123", "nickname" : "ygirl2", "iid" : 2 }
            }*/

        }else {
            System.out.println("查询非id");
           iter = doc.find(com.mongodb.client.model.Filters.eq(item, para)).batchSize(20);
        }
        iter.forEach(new Block<Document>() {
            public void apply(Document document) {
                AnjuanjiItem MyItem=new AnjuanjiItem();
                ObjectId id=document.getObjectId("_id");
                String OTHEROWNER=document.getString("OTHEROWNER");
                String OLDOWNER=document.getString("OLDOWNER");
                String OWNER=document.getString("OWNER");
                String  GATHERDATE=document.getString("GATHERDATE");
                String HOUSEPLACE=document.getString("HOUSEPLACE");
                String PIGEONHOLEDATE=document.getString("PIGEONHOLEDATE");
                MyItem.set_id(id);
                MyItem.setGATHERDATE(GATHERDATE.toString());
                MyItem.setOTHEROWNER(OTHEROWNER);
                MyItem.setOWNER(OWNER);
                MyItem.setOLDOWNER(OLDOWNER);
                MyItem.setHOUSEPLACE(HOUSEPLACE);
                MyItem.setPIGEONHOLEDATE(PIGEONHOLEDATE.toString());
                array.add(MyItem);
                  /*  System.out.println(id);
                    System.out.println(GATHERDATE);
                    System.out.println(OWNER);
                    */
            }
        });
    return  array;
    }



}
