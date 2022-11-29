package ru.senla.task8.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.senla.task8.Message;
import ru.senla.task8.ToDoRequest;
import ru.senla.task8.ToDoRequestById;
import ru.senla.task8.ToDoResponse;
import ru.senla.task8.ToDoServiceGrpc;
import ru.senla.task8.ToDoUpdateRequestById;

import java.util.Iterator;
import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 нояб.
 */
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        ToDoServiceGrpc.ToDoServiceBlockingStub stub
                = ToDoServiceGrpc.newBlockingStub(channel);

        out.println("You successful connected to server!");

        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (input != -1) {
            out.println(
                    """
                    
                    Menu:
                    1. get all
                    2. get by id
                    3. add
                    4. update
                    5. delete
                    
                    -1. disconnect
                    """
            );

            out.print("> ");
            input = sc.nextInt();

            switch (input) {
                case 1 -> {
                    int i = 0;
                    for (Iterator<ToDoResponse> it = stub.getAll(null); it.hasNext(); ) {
                        i += 1;
                        ToDoResponse toDoResponse = it.next();

                        String id = toDoResponse.getId();
                        String name = toDoResponse.getName();
                        boolean completed = toDoResponse.getCompleted();
                        String createdOn = toDoResponse.getCreatedOn();

                        out.println(i + ") " + name + " [ " + id + " ]" + " " + "[ " + createdOn + " ]" + " " + "[ " + completed + " ]");
                    }

                    if (i == 0) {
                        out.println("Relax bro! You all completed!");
                    }
                }
                case 2 -> {
                    out.println("enter id of todo:");
                    sc.nextLine();
                    String idForFind = sc.nextLine();

                    ToDoResponse toDoResponse = stub.getById(ToDoRequestById.newBuilder().setId(idForFind).build());

                    printToDoResponse(toDoResponse);
                }
                case 3 -> {
                    out.println("enter name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    ToDoResponse toDoResponse = stub.add(
                            ToDoRequest.newBuilder()
                                    .setName(name)
                                    .setCompleted(false)
                                    .build()
                    );

                    printToDoResponse(toDoResponse);
                }
                case 4 -> {
                    out.println("enter id: ");
                    sc.nextLine();
                    String id = sc.nextLine();
                    out.println("enter name: ");
                    String name = sc.nextLine();
                    out.println("enter completed: ");
                    boolean completed = sc.nextBoolean();

                    ToDoResponse toDoResponse = stub.update(
                            ToDoUpdateRequestById.newBuilder()
                                    .setId(id)
                                    .setName(name)
                                    .setCompleted(completed)
                                    .build()
                    );

                    printToDoResponse(toDoResponse);
                }
                case 5 -> {
                    out.println("enter id of todo:");
                    sc.nextLine();
                    String idForFind = sc.nextLine();

                    Message message = stub.delete(ToDoRequestById.newBuilder().setId(idForFind).build());

                    out.println(message.getMessage());
                }
                case -1 -> {}
                default -> err.println("Invalid enter!");
            }
        }

        channel.shutdownNow();
    }

    private static void printToDoResponse(ToDoResponse toDoResponse) {
        String id = toDoResponse.getId();
        String name = toDoResponse.getName();
        boolean completed = toDoResponse.getCompleted();
        String createdOn = toDoResponse.getCreatedOn();

        out.println("Response from server: " + name + " [ " + id + " ]" + " " + "[ " + createdOn + " ]" + " " + "[ " + completed + " ]");
    }

}
