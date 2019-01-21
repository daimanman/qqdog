import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.bson.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.man.mongo.MongoDBHelper;
import com.man.utils.IdWorker;
import com.mongodb.client.MongoCollection;

public class TestMongo {

	IdWorker idWorker = new IdWorker();
	@Test
	public void test02() {
		String databaseName = "bdata_16";
	    String collectionName = "quser_info";
	    MongoCollection<Document> firstCollection;
	    MongoDBHelper.connect(databaseName);
	    firstCollection =  MongoDBHelper.getCollection(collectionName);
	    String json = MongoDBHelper.getDocumentFirst(firstCollection);
        System.out.println(json);
	}
	
	@Test
	public void test03() throws FileNotFoundException, IOException {
		String content  = IOUtils.toString(new FileInputStream(new File("D:\\emot.json")));
		List<Document> docs = JSON.parseArray(content,Document.class);
		String databaseName = "bdata_16";
	    String collectionName = "qemot_info";
	    MongoCollection<Document> firstCollection;
	    MongoDBHelper.connect(databaseName);
	    firstCollection =  MongoDBHelper.getCollection(collectionName);
	    MongoDBHelper.insertManyDocument(firstCollection, docs);
		System.out.println(content);
	}
	
	@Test
	public void test04() throws IOException{
		
		String content  = IOUtils.toString(new FileInputStream(new File("D:\\emot.json")));
		List<Document> docs = JSON.parseArray(content,Document.class);
		String databaseName = "bdata_16";
	    String collectionName = "qemot_info";
	    MongoCollection<Document> firstCollection;
	    MongoDBHelper.connect(databaseName);
	    firstCollection =  MongoDBHelper.getCollection(collectionName);
	    for(Document doc : docs) {
	    	doc.append("_id",idWorker.nextId());
	    }
	    MongoDBHelper.insertManyDocument(firstCollection, docs);
	}
	
	@Test
	public void test05() throws IOException{
		String databaseName = "bdata_16";
	    String collectionName = "qmsg_info";
	    MongoCollection<Document> firstCollection;
	    MongoDBHelper.connect(databaseName);
	   // MongoDBHelper.database.createCollection(collectionName);
	    firstCollection =  MongoDBHelper.getCollection(collectionName);
	    String content  = IOUtils.toString(new FileInputStream(new File("D:\\msg.json")));
		List<Document> docs = JSON.parseArray(content,Document.class);
		MongoDBHelper.insertManyDocument(firstCollection, docs);
	}
}
