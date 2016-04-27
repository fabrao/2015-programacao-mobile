

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

public class Servidor {
    public static void main(String[] args) {
        try {
            //Starting the REST service
            HttpServer server = HttpServerFactory.create("http://localhost:4000/");
            server.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}
