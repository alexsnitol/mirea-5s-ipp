package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyHousePropertyDto extends HousingPropertyDto {

    private FamilyHouseDto familyHouse;

}
