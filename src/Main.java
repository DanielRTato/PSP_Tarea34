import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("introduce una URL");
        String url = scanner.nextLine();

        HttpClient cliente = HttpClient.newHttpClient(); // Crear el cliente HTTP

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) // dirección del recurso
                .GET() // pide información
                .timeout(Duration.ofSeconds(5L)) // Tiempo de espera de 5 segundos
                .build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("El código de estado es: " + response.statusCode());
            System.out.println("El tipo de contenido es: " + response.headers().firstValue("content-type").orElse("Desconocido"));

            if (response.statusCode() == 200) {
                try (PrintWriter escritor = new PrintWriter("inspector.html");) {
                    System.out.println("La petición fue exitosa, guardando el HTML en un fichero...");

                    escritor.write(response.body());
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la petición: " + e.getMessage());
        }
    }
}
