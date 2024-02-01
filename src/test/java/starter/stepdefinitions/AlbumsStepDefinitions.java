package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class AlbumsStepDefinitions {
    private RequestSpecification requestSpec;

    @Given("que el usuario solicita la lista de álbumes")
    public void queElUsuarioSolicitaLaListaDeÁlbumes() {
        // Crear una nueva solicitud HTTP
        requestSpec = RestAssured.given();
    }

    @When("la solicitud se realiza correctamente")
    public void laSolicitudSeRealizaCorrectamente() {
        // Realizar la solicitud GET al endpoint
        Response response = requestSpec.get("https://jsonplaceholder.typicode.com/albums");

        // Comprobar que la respuesta HTTP tiene código 200
        assertEquals(200, response.statusCode());

    }

    @Then("la respuesta debe contener los siguientes datos")
    public void laRespuestaDebeContenerLosSiguientesDatos(DataTable dataTable) {
        Response response = requestSpec.get("https://jsonplaceholder.typicode.com/albums");
        // Convertir la tabla de datos en una lista de mapas
        List<Map<String, String>> expectedAlbums = dataTable.asMaps(String.class, String.class);

        // Extraer los álbumes de la respuesta HTTP
        List<Map<String, Object>> actualAlbums = response.jsonPath().getList("$");

        // Comparar los álbumes esperados con los reales
        for (int i = 0; i < expectedAlbums.size(); i++) {
            Map<String, String> expectedAlbum = expectedAlbums.get(i);
            Map<String, Object> actualAlbum = actualAlbums.get(i);

            assertEquals(expectedAlbum.get("id"), String.valueOf(actualAlbum.get("id")));
            assertEquals(expectedAlbum.get("title"), actualAlbum.get("title"));
            assertEquals(expectedAlbum.get("userId"), String.valueOf(actualAlbum.get("userId")));
        }
    }
    public void getAlbumsFromAPIJavaBasic() {
        try {
            // Establecer la URL del API
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");

            // Abrir la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Establecer el método de solicitud
            conn.setRequestMethod("GET");

            // Obtener la respuesta del servidor
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // Si la respuesta es exitosa
                // Leer la respuesta del servidor
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                // Imprimir la respuesta del servidor
                System.out.println("Respuesta del API:");
                System.out.println(response.toString());
            } else {
                System.out.println("Error al conectar al API. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        try {
            // Establecer la URL del token endpoint
            URL url = new URL("https://oauth2.googleapis.com/token");

            // Abrir la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Establecer el método de solicitud
            conn.setRequestMethod("POST");

            // Establecer los parámetros de la solicitud
            String data = "grant_type=client_credentials&client_id=" + "clientId" + "&client_secret=" + "clientSecret";
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(data.length()));
            conn.setDoOutput(true);

            // Obtener la respuesta del servidor
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // Si la respuesta es exitosa
                // Leer la respuesta del servidor
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();

                // Extraer el token de acceso de la respuesta
                String token = response.toString().split("\"access_token\":\"")[1].split("\"")[0];

                return token;
            } else {
                System.out.println("Error al obtener el token de acceso. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}