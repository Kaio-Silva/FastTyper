package Database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Sorts.descending;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Db {
    public static String dbName = "fastTyperDb";
    public static String cllcName = "rank";

    public static Dotenv dotenv = Dotenv.load();

    public static String connectionString = "mongodb+srv://" + dotenv.get("MONGO_USER") + ":" + dotenv.get("MONGO_PWD")
            + "@cluster0.jts6smd.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    public static ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();

    public static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();

    public static String[] getRank() {
        String result[] = new String[11];
        try {

            MongoClient mongoClient = MongoClients.create(settings);
            MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(cllcName);
            List<Document> documents = collection.find().limit(10).sort(descending("Pontos")).into(new ArrayList<>());
            int numIndex = 0;
            for (Document document : documents) {
                result[numIndex] = document.toJson();
                numIndex++;
            }
            return result;
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPlayerPos(String playername) {
        int result = 0;
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(cllcName);
            int numIndex = 1;
            for (Document document : collection.find().sort(descending("pontos"))) {
                if (document.getString("Nome").equals(playername)) {
                    result = numIndex;
                }
                numIndex++;
            }
            return result;
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertPlayer(String playername, double points) {
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(cllcName);

            collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("Nome", playername)
                    .append("Pontos", points));

            System.out.println("Inserido com sucesso");

        } catch (MongoException e) {
            System.out.println("Falha");
        }
    }
}
