package com.quinnox.qsmr.db;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.quinnox.qsmr.util.QSMRUtil;


/**
 * Java + MongoDB Hello world Example
 * 
 */
public class QsmrDao {
	
  public static void insertQSMRRecord(String projectName, String revenue, String margins, String csat, String hcutilization, String totalHeadCount, String billableHeadCount, String billingUtilization, 
		  String subContractors, String weeklyBillableHC, String goals, String keyissues, String innovation, String lowlights, String highlights,
		  
		  String dependicies, String decisions) {
	  
	  

    try {

	/**** Connect to MongoDB ****/ 
	// Since 2.10.0, uses MongoClient
	MongoClient mongo = new MongoClient("localhost", 27017);

	/**** Get database ****/
	// if database doesn't exists, MongoDB will create it for you
	DB db = mongo.getDB("nodetest1");

	/**** Get collection / table from 'testdb' ****/
	// if collection doesn't exists, MongoDB will create it for you
	DBCollection table = db.getCollection("user");

	/**** Insert ****/
	// create a document to store key and value
	BasicDBObject document = new BasicDBObject();
	document.put("ProjectName", QSMRUtil.replaceCommaWithHash(projectName));
	document.put("Revenue", QSMRUtil.replaceCommaWithHash(revenue));
	document.put("Margins", QSMRUtil.replaceCommaWithHash(margins));
	document.put("CSAT", QSMRUtil.replaceCommaWithHash(csat));
	
	document.put("HCUtilization", QSMRUtil.replaceCommaWithHash(hcutilization));
	document.put("TotalHeadCount", QSMRUtil.replaceCommaWithHash(totalHeadCount));
		
	document.put("BillableHeadCount", QSMRUtil.replaceCommaWithHash(billableHeadCount));
	document.put("BillingUtilization", QSMRUtil.replaceCommaWithHash(billingUtilization));
	document.put("SubContractors", QSMRUtil.replaceCommaWithHash(subContractors));
	document.put("WeeklyBillableHC", QSMRUtil.replaceCommaWithHash(weeklyBillableHC));
	
	document.put("Goals", QSMRUtil.replaceCommaWithHash(goals));
	document.put("KEYISSUES", QSMRUtil.replaceCommaWithHash(keyissues));
	
	document.put("INNOVATION", QSMRUtil.replaceCommaWithHash(innovation));
	document.put("LOWLIGHTS", QSMRUtil.replaceCommaWithHash(lowlights));
	document.put("HIGHLIGHTS",QSMRUtil.replaceCommaWithHash(highlights));
	
	
	document.put("DEPENDICIES", QSMRUtil.replaceCommaWithHash(dependicies));
	document.put("DECISIONS", QSMRUtil.replaceCommaWithHash(decisions));
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	String date = sdf.format(new Date()); 
	document.put("DATE", date);
	table.insert(document);

	System.out.println("Done");
	
    } catch (Exception e) {
	e.printStackTrace();
    } 

  }
  public static Map<String,String> findData(String dbName, String collectionName, String projectName, String date) throws UnknownHostException {
	  String value ="";
	  /**Connecting to database*/	
	  DBCollection table= getConnection(dbName, collectionName);
	  
	  	  		
	//  DBCursor cursor = table.find().sort(new BasicDBObject("projectName",-1)).limit(10);
	  
	//  System.out.println("------------------------" + cursor.next().get("Revenue").toString());
	  
	  BasicDBObject searchQuery = new BasicDBObject();  
	 // searchQuery.put("DATE", "28-Apr-2016");
	  searchQuery.put("ProjectName", projectName);
	  searchQuery.put("DATE", date);  
	 	     
	  DBCursor cursor1 = table.find(searchQuery);  
	 
	
	  
	  Map <String,String> map = null;
	  /**iterating over the documents got from the database*/
	  while (cursor1.hasNext()) {
	  			
	  DBObject obj= cursor1.next();
	  
	 
	  	  
	  /**documentToMapUtility is coded to convert the document received from database to key value pairs and put it inside a map*/
	  String obj1 = obj.toString();
	 // System.out.println("Obj1 " + obj);
	  
	  	map = documentToMapUtility (obj1);
	 
	 
	   } 
	   return map;
	  }   	 
  public static Map<String,String> documentToMapUtility(String s){
	  System.out.println("documentToMapUtility starting java.io.PrintStream.println()");
	  
	 

      s= s.substring(1,s.length()-1);
      
      System.out.println(" S " +s);

      String sArr[]= s.split(",");
      
      /*for (int i = 0; i < sArr.length; i++) {
          
          System.out.print(sArr[i]);
       }
*/
      System.out.print("sarr length ====" +sArr.length);
      
      Map<String,String> map = new LinkedHashMap<String,String>();
        
      
      
      for(int i=1;i<sArr.length;i++){
    	  
    	              if(!sArr[i].contains("$date")){
    	  
    	              String keyValue[]= sArr[i].split(":");
    	              
    	              System.out.println(keyValue[0]+","+keyValue[1] + "i ======" +i);
    	  
    	              map.put(keyValue[0],keyValue[1]);
    	  
    	             // System.out.println(keyValue[0]+","+keyValue[1]);
    	  
    	              }else{
    	  
    	                  String keyValue[]= sArr[i].split(":");
    	  
    	                  map.put(keyValue[0],keyValue[2]);
    	                  System.out.println("else " + keyValue[0]+","+keyValue[1]);
    	              }
    	  
    	          }

      System.out.println("documentToMapUtility starting java.io.PrintStream.println()" +map.toString());
      return map;

  }
  public static DBCollection getConnection(String dbName, String collectionName)throws UnknownHostException {
		
      /** Connecting to MongoD B */
	MongoClient mongo = new MongoClient("localhost", 27017);

	/**Gets database, incase if the database is not existing
        MongoDB Creates it for you*/
	DB db = mongo.getDB(dbName);

	/**Gets collection / table from database specified if
        collection doesn't exists, MongoDB will create it for
        you*/
	DBCollection table = db.getCollection(collectionName);
	return table;
}

  /**Utility to remove un wanted contents*/
	public static String subStringUtility (String s){
		
		return s.substring(2,s.length()-2);
	}
	
public static String subStringUtilityDate (String s){
		
		return s.substring(2,s.length()-1);
	}

	public static String parseValuefromMap(Map<String,String> map, String paramKey){
		
		/**Getting the Entery Set from the map  */
		 Set<Entry<String,String>> set=	map.entrySet();
		 
		 String value1 ="";

		 /**Getting the Iterator to iterate the entry Set*/
		 Iterator<Entry<String,String>> itr= set.iterator();
		 		
		 /**loop to put ever Key value pair to CarTO object*/		  
		   while(itr.hasNext()){
		 	
		 	    	Entry<String, String> entry = itr.next();
		 	    	String key=entry.getKey();

		           /**Removing the unwanted from the keys*/
		 	    	key = subStringUtility(key);
		 	    			            
		            if(key.equalsIgnoreCase(paramKey)){
		            	 value1 = entry.getValue();
		            	System.out.println("value1 =======" +value1);
		            }
		          
		     }  
		return value1;
	}
	
	 public static Set<String> listProjectName(String dbName, String collectionName){
         Set<String> list = new HashSet<String>();
          try {
                 
                  DBCollection table= getConnection(dbName, collectionName);
                         
                   DBCursor cursor = table.find().sort(new BasicDBObject("ProjectName",-1)).limit(10);
                                     
                   for(int i=0;i<cursor.count();i++){
                           if(cursor!=null)
                                   list.add(cursor.next().get("ProjectName").toString());
                   }
                                     
                   
         } catch (UnknownHostException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
          return list;
 }
	 public static Set<String> listDate(String dbName, String collectionName, String projectName){
         Set<String> list = new HashSet<String>();
          try {
                 
                  DBCollection table= getConnection(dbName, collectionName);
                  BasicDBObject whereQuery = new BasicDBObject();
                  whereQuery.put("ProjectName", projectName);
                         
                  // DBCursor cursor = table.find().sort(new BasicDBObject("DATE",-1)).limit(10);
                                     
                   DBCursor cursor = table.find(whereQuery).sort(new BasicDBObject("DATE",-1)).limit(10);
       		    for (DBObject dbObject : cursor) {
       		    	 if(cursor!=null)
                           // System.out.println(cursor.next().get("DATE").toString());
       		    	 list.add(cursor.next().get("DATE").toString());
       		    }            
                   
         } catch (UnknownHostException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
          return list;
 }
	
	 public static Map<String, String> getPreviousMonthValue(String dbName, String collectionName){
		 
		 
		 Map<String, String> map =null;
		try {
			DBCollection table= getConnection(dbName, collectionName);

				/**** Get collection / table from 'testdb' ****/
				// if collection doesn't exists, MongoDB will create it for you
				 BasicDBObject query = new BasicDBObject();
				    //query.put("_id", new BasicDBObject("$lt", "DATE"));
				    DBCursor cursor = table.find(query).sort(new BasicDBObject("DATE",-1)).limit(1);
				    map = null;
					  /**iterating over the documents got from the database*/
					  while (cursor.hasNext()) {
					  			
					  DBObject obj= cursor.next();
					  
					 
					  	  
					  /**documentToMapUtility is coded to convert the document received from database to key value pairs and put it inside a map*/
					  String obj1 = obj.toString();
					 // System.out.println("Obj1 " + obj);
					  
					  	map = documentToMapUtility (obj1);
					  }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				  	return map;
	 }
}
