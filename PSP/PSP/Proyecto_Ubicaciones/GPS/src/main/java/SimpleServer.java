import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.Executors;

public class SimpleServer {

    private static final int PORT = 9090; // Puerto del servidor
    private static final List<Position> positions = Collections.synchronizedList(new ArrayList<>());
    private static final Gson gson = new Gson();
    private static final String HTML_RESOURCE = "/map.html"; // Recurso dentro de resources

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT) , 0);

        // Servir map.html desde resources
        server.createContext("/map.html" , exchange -> {
            InputStream is = SimpleServer.class.getResourceAsStream(HTML_RESOURCE);
            if (is == null) {
                exchange.sendResponseHeaders(404 , 0);
                exchange.getResponseBody().close();
                return;
            }

            byte[] bytes = is.readAllBytes();
            exchange.getResponseHeaders().add("Content-Type" , "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200 , bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
            is.close();
        });

        // API para obtener posiciones
        server.createContext("/api/positions" , exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = gson.toJson(positions);
                exchange.getResponseHeaders().add("Content-Type" , "application/json");
                exchange.sendResponseHeaders(200 , response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            } else if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes());
                Position pos = gson.fromJson(body , Position.class);

                synchronized (positions) {
                    positions.removeIf(p -> p.getDeviceId().equals(pos.getDeviceId()));
                    positions.add(pos);
                }

                String response = "OK";
                exchange.sendResponseHeaders(200 , response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
                exchange.getResponseBody().close();
            } else {
                exchange.sendResponseHeaders(405 , -1);
            }
        });

        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
        System.out.println("Servidor iniciado en http://192.168.2.174:" + PORT + "/map.html");
    }
}