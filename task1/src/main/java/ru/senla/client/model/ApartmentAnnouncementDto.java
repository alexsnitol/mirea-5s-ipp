package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartmentAnnouncementDto extends HousingAnnouncementDto {

    private ApartmentPropertyDto property;

}
