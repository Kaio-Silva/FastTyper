package Database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.ListCollectionNamesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

import org.bson.Document;

public class MongoDb {
    public static MongoClient connect(String dbname) {
        Dotenv dotenv = Dotenv.load();
        String connectionString = "mongodb+srv://" + dotenv.get("MONGO_USER") + ":" + dotenv.get("MONGO_PWD")
                + "@cluster0.jts6smd.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        try (
                MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase(dbname);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

                return mongoClient;

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        connect("fastTyperDb");
    }

}
