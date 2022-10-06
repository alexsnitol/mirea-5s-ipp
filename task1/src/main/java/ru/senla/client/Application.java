package ru.senla.client;

import org.glassfish.jersey.jackson.JacksonFeature;
import ru.senla.client.model.ApartmentAnnouncementDto;
import ru.senla.client.model.JwtToken;
import ru.senla.client.model.RequestAuthDto;
import ru.senla.client.model.UserDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

public class Application {

    private static String HOST_URL = "http://localhost:8080/real-estate-market/api";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(HOST_URL).path("auth");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        RequestAuthDto requestAuth = new RequestAuthDto();
        requestAuth.setUsername("admin");
        requestAuth.setPassword("admin");

        Entity<RequestAuthDto> requestAuthDtoEntity = Entity.json(requestAuth);

        Response response = invocationBuilder.post(requestAuthDtoEntity);

        JwtToken jwtToken = response.readEntity(JwtToken.class);

        client = ClientBuilder.newClient();
        WebTarget userTarget = client.target(HOST_URL).path("/users/current");
        invocationBuilder = userTarget.request(MediaType.APPLICATION_JSON).header("Authorization", jwtToken.getToken());

        response = invocationBuilder.get();

        UserDto currentUser = response.readEntity(UserDto.class);

        System.out.println(currentUser.toString());

    }

}
