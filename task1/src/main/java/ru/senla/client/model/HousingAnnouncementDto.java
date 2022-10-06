package ru.senla.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HousingAnnouncementDto extends AnnouncementDto {

    private HousingAnnouncementTypeEnum type;

}
