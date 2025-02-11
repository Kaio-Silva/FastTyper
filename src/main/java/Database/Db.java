package Database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;

import Utils.HandleText;

import static com.mongodb.client.model.Sorts.descending;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class Db {  



    public static String dbName = "fastTyperDb";
    public static String cllcName = "rank";

    public static Dotenv dotenv = Dotenv.configure().directory("src/main/java").load();

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
                String[] finalResult = new String[11];
                try {
                    MongoClient mongoClient = MongoClients.create(settings);
                    MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(cllcName);
                    List<Document> documents = collection.find().limit(10).sort(descending("Pontos")).into(new ArrayList<>());
                    int numIndex = 0;
                    for (Document document : documents) {
                        result[numIndex] = document.toJson();
                        numIndex++;
                    }
                    
                    for (int x = 0; x < result.length; x++) {
                        if (result[x] == null)
                            break;
                        Pattern pattern = Pattern.compile("[\\{\\}:,\\\"]", Pattern.MULTILINE);
                        Matcher matcher = pattern.matcher(result[x]);
                        finalResult[x] = matcher.replaceAll("")
                        .replace(" Nome",x+1 + "º")
                        .replace("Pontos", "- Pontos:")
                        .replace("_id","")
                        .replace("$oid","")
                        .substring(26);
        
                    }

                    for(int i = 0; i < result.length; i++){   
                        HandleText.align(result[i] == null ? "" : finalResult[i], "center", true, "");
                    }

                    return finalResult;
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
        } catch (MongoException e) {
            System.out.println("\nFalha na inserção");
        }
    }

    public static void updateByName(String name, double points) {
        try {
            MongoClient mongoClient = MongoClients.create(settings);
            MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(cllcName);

            Bson update = Updates.combine(
                Updates.set("Pontos", points));
            
            collection.updateOne(new Document()
                    .append("Nome", name),
                    update
            );
        } catch (MongoException e) {
            System.out.println("\nFalha em update");
        }
    }
}
