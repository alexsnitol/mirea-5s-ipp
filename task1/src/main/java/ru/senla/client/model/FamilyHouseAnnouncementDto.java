package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyHouseAnnouncementDto extends HousingAnnouncementDto {

    private FamilyHousePropertyDto property;

}
