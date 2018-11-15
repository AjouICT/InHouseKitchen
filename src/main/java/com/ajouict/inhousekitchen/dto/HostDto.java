package com.ajouict.inhousekitchen.dto;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.Location;
import com.ajouict.inhousekitchen.domain.MenusInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HostDto {
    private String introduction;
    private String contact_info;
    private String menu_name;
    private String menu_price;
    private String menu_description;
    @JsonProperty(value = "date_range")
    private String date_range;
    private String location_latitude;
    private String location_longitude;

    @Builder
    public HostDto(String introduction, String contact_info, String menu_name, String menu_price, String menu_description, String date_range, String location_latitude, String location_longitude) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.menu_description = menu_description;
        this.date_range = date_range;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
    }

    public Host _toHost(){
        return Host.builder()
                .introduction(this.introduction)
                .contact_info(this.contact_info)
                .location(Location.builder().latitude(Double.parseDouble(this.location_latitude)).longitude(Double.parseDouble(this.location_longitude)).build())
                .menusInfo(MenusInfo.builder().description(this.menu_description).name(this.menu_name).price(Double.parseDouble(this.menu_price)).build())
                .availablePeriod(this.date_range)
                .build();
    }
}
