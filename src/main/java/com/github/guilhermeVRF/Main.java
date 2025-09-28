package com.github.guilhermeVRF;

import com.github.guilhermeVRF.Controllers.UserController;
import com.github.guilhermeVRF.Repositories.UserRepository;
import com.github.guilhermeVRF.Services.UserService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_URI");
        if(connectionString == null){
            System.err.println("MONGO_URI environment variable not set.");
            return;
        }
        MongoClient mongoClient = MongoClients.create(connectionString);
        if(mongoClient == null) {
            System.err.println("Failed to create MongoClient. Check your connection string.");
            return;
        }
        MongoDatabase database = mongoClient.getDatabase("usersDB");

        Javalin server = Javalin.create(
            setting -> {
                setting.bundledPlugins.enableCors(
                    cors -> {cors.addRule(
                        it -> {
                            it.anyHost();}
                        );
                    }
                );
            }
        ).start(8080);

        System.out.println("Servidor iniciado na porta 8080");

        UserRepository userRepository = new UserRepository(database.getCollection("users"));
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        server.get("/", ctx -> ctx.result("Servidor está funcionando!"));
        server.post("/users", userController::createUser);
        server.get("/users", userController::getAllUsers);
        server.get("/users/{id}", userController::getUser);
        server.put("/users/{id}", userController::updateUser);
        server.delete("/users/{id}", userController::deleteUser);
    }
}
