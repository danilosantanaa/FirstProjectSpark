import java.nio.charset.StandardCharsets;

import static spark.Spark.*;

public class Main {
    public static void main(String [] args) {
        port(8080); // A porta padrão do servidor web
        staticFileLocation("/public");
        Operacoes op = new Operacoes();
        get("/", (request, response) -> {
            response.header("Content-Type", "text/html; charset=utf-8");
            response.status(200);
            return new View(request).index();
        });
        get("/calcular/*", (request, response) -> {
            response.header("Content-Type", "text/html; charset=utf-8");
            response.status(200);
            String dados = new String(op.calculate(request.splat()[0]).getBytes(), "UTF-8");
            return new View(request).response().replace("{{ response }}", dados);
        });
    }
}
