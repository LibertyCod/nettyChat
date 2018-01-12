package com.liberty.server;

import com.liberty.server.dal.domain.ChatMessageDO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/**
 * @author Binbin Wang
 * @date 2018/1/12
 */
public class MongoDBConnectionFactory {

    private static final String MONGODB_URL = "localhost";

    private static final int MONGODB_PORT = 27017;

    private static final String MONGODB_PSD = "";

    private MongoDBConnectionFactory() {}

    public static MongoDatabase getMongoDatabase() {
        MongoClient mongoClient = new MongoClient(MONGODB_URL, MONGODB_PORT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("netchat");
        return mongoDatabase;
    }

    public static void main(String[] args) {
        MongoDatabase mongoDatabase = MongoDBConnectionFactory.getMongoDatabase();
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("chat_message");
        Document doument = new Document();
        ChatMessageDO chatMessageDO = new ChatMessageDO();
        chatMessageDO.setUserId("1234");
        chatMessageDO.setContent("你好");
        chatMessageDO.setCreateTime(new Date());
        doument.append("json", chatMessageDO.toString());
        mongoCollection.insertOne(doument);
    }

}
