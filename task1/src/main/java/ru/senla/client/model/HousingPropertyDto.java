package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HousingPropertyDto extends PropertyDto {

    private Short numberOfRooms;

    private String renovationType;

}
