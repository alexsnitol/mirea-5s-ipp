package ru.senla.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseDto {

    private Long id;

    private AddressDto address;

    private Short numberOfFloors;

    private Short buildingYear;

    private String houseMaterial;

    private HousingTypeEnum housingType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HouseTypeEnum houseType;

}
