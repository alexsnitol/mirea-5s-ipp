package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentPropertyDto extends HousingPropertyDto {

    private ApartmentHouseDto apartmentHouse;

    private String apartmentNumber;

    private Integer floor;

}
