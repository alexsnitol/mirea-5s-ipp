package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentHouseDto extends HouseDto {

    private Boolean elevator;

    private Integer numberOfApartmentProperties;

}
