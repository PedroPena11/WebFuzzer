import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MotorHttp {
    private static final HttpClient cliente = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    public static int requestStatus(String url){
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent",UserAgent.gainRandom())
                    .GET()
                    .build();
            return cliente.send(request, HttpResponse.BodyHandlers.discarding()).statusCode();
        }catch (Exception e){
            return -1;
        }
    }
}
