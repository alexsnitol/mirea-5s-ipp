package ru.senla.task8.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 нояб.
 */
public class GrpcServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new ToDoServiceImpl()).build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }

}
