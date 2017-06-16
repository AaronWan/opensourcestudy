import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static spark.Spark.*;
@Slf4j
public class HelloWorld {
    public static final Gson gson=new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        initExceptionHandler((e) -> System.out.println("Uh-oh"));
        port(8080);
        get("/hello", (req, res) -> "Hello World");
        get("/hello2", (req, res) -> "Hello World2");
    }

    private Consumer<Exception> initExceptionHandler = (e) -> {
        System.exit(100);
    };
}