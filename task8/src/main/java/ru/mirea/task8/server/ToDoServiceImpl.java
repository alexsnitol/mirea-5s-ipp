package ru.mirea.task8.server;

import io.grpc.stub.StreamObserver;
import ru.mirea.task8.Message;
import ru.mirea.task8.ToDoRequest;
import ru.mirea.task8.ToDoRequestById;
import ru.mirea.task8.ToDoResponse;
import ru.mirea.task8.ToDoServiceGrpc;
import ru.mirea.task8.ToDoUpdateRequestById;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * @author Alexander Slotin (<a href="https://github.com/alexsnitol">@alexsnitol</a>) <p>
 * 2022 нояб.
 */
public class ToDoServiceImpl extends ToDoServiceGrpc.ToDoServiceImplBase {


    private static Map<String, ToDoResponse> toDoMap = new HashMap<>();


    @Override
    public void getAll(ToDoRequest request, StreamObserver<ToDoResponse> responseObserver) {
        out.println("Request received from client on get all");

        toDoMap.values().forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }

    @Override
    public void getById(ToDoRequestById request, StreamObserver<ToDoResponse> responseObserver) {
        out.println("Request received from client on get by id:\n" + request);

        try {
            responseObserver.onNext(toDoMap.get(request.getId()));
        } catch (NullPointerException e) {
            err.println("ToDo with id " + request.getId() + " not found!");
        }

        responseObserver.onCompleted();
    }

    @Override
    public void add(ToDoRequest request, StreamObserver<ToDoResponse> responseObserver) {
        out.println("Request received from client on add:\n" + request);

        ToDoResponse toDoResponse =
                ToDoResponse.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setName(request.getName())
                    .setCompleted(request.getCompleted())
                    .setCreatedOn(LocalDateTime.now().toString())
                    .build();

        toDoMap.put(toDoResponse.getId(), toDoResponse);

        responseObserver.onNext(toDoResponse);

        responseObserver.onCompleted();
    }

    @Override
    public void update(ToDoUpdateRequestById request, StreamObserver<ToDoResponse> responseObserver) {
        out.println("Request received from client on add:\n" + request);

        ToDoResponse toDoResponse =
                ToDoResponse.newBuilder()
                        .setId(request.getId())
                        .setName(request.getName())
                        .setCompleted(request.getCompleted())
                        .setCreatedOn(LocalDateTime.now().toString())
                        .build();

        toDoMap.replace(toDoResponse.getId(), toDoResponse);

        responseObserver.onNext(toDoResponse);

        responseObserver.onCompleted();
    }

    @Override
    public void delete(ToDoRequestById request, StreamObserver<Message> responseObserver) {
        out.println("Request received from client on get by id:\n" + request);

        try {
            toDoMap.remove(request.getId());

            responseObserver.onNext(
                    Message.newBuilder()
                            .setMessage("ToDo successful deleted!")
                            .build()
            );
        } catch (NullPointerException e) {
            err.println("ToDo with id " + request.getId() + " not found!");
        }

        responseObserver.onCompleted();
    }

}
