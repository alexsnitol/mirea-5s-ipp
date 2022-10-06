package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandAnnouncementDto extends AnnouncementDto {

    private NonHousingAnnouncementTypeEnum type;

    private LandPropertyDto property;

}
