import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ComparadorURLs.pedirURLs();
        ComparadorURLs.compararURLs();

    }
}
