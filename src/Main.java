import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("introduce la primera URL:");
        String url1 = scanner.nextLine();
        System.out.println("introduce la segunda URL:");
        String url2 = scanner.nextLine();

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url1))
                .build();

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url2))
                .build();

        try {
            long tiempoURL1Inicio = System.currentTimeMillis();
            HttpResponse<String> response1 = cliente.send(request1, HttpResponse.BodyHandlers.ofString());
            long tiempoURL1Fin = System.currentTimeMillis();
            int tamaño = response1.body().length();

            long tiempoURL2Inicio = System.currentTimeMillis();
            HttpResponse<String> response2 = cliente.send(request2, HttpResponse.BodyHandlers.ofString());
            long tiempoURL2Fin = System.currentTimeMillis();
            int tamaño2 = response2.body().length();

            if ((tiempoURL1Fin - tiempoURL1Inicio) < (tiempoURL2Fin - tiempoURL2Inicio)) {
                System.out.println("La web más rápida ha sido:  " + url1 + " con " + (tiempoURL1Fin - tiempoURL1Inicio) + " ms");
            } else if ((tiempoURL1Fin - tiempoURL1Inicio) > (tiempoURL2Fin - tiempoURL2Inicio)) {
                System.out.println("La web más rápida ha sido " + url2 + " con " + (tiempoURL2Fin - tiempoURL2Inicio) + " ms");
            } else {
                System.out.println("Ambas URLs tienen el mismo tiempo de respuesta.");
            }

            if (tamaño > tamaño2) {
                System.out.println("La web con más contenido ha sido: " + url1 + " con " + tamaño + " caracteres.");
            } else if (tamaño < tamaño2) {
                System.out.println("La web con más contenido ha sido: " + url2 + " con " + tamaño2 + " caracteres.");
            } else {
                System.out.println("Ambas URLs tienen el mismo tamaño de contenido.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al acceder a las URLs: " + e.getMessage());
        }

    }
}
