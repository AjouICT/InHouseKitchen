package com.ajouict.inhousekitchen.dto;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.Location;
import com.ajouict.inhousekitchen.domain.MenusInfo;
import com.ajouict.inhousekitchen.domain.VisitRate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;

@Getter
@Setter
public class HostDto {
    private String introduction;
    private String contactInfo;

    private String menuName;

    private String menuPrice;

    private String menuDescription;
    private String dateRange;
    private String locationLatitude;
    private String locationLongitude;

    @Nullable
    private Double aboveTwoTimesVisitedRate;
    @Nullable
    private Double aboveThreeTimesVisitedRate;

    @Builder
    public HostDto(String introduction, String contact_info, String menu_name, String menu_price, String menu_description, String date_range, String location_latitude,
                   String location_longitude, Double aboveTwoTimesVisitedRate, Double aboveThreeTimesVisitedRate) {
        this.introduction = introduction;
        this.contactInfo = contact_info;
        this.menuName = menu_name;
        this.menuPrice = menu_price;
        this.menuDescription = menu_description;
        this.dateRange = date_range;
        this.locationLatitude = location_latitude;
        this.locationLongitude = location_longitude;
        this.aboveTwoTimesVisitedRate = aboveTwoTimesVisitedRate;
        this.aboveThreeTimesVisitedRate = aboveThreeTimesVisitedRate;
    }

    public Host _toHost(){
        return Host.builder()
                .introduction(this.introduction)
                .contactInfo(this.contactInfo)
                .location(Location.builder().latitude(Double.parseDouble(this.locationLatitude)).longitude(Double.parseDouble(this.locationLongitude)).build())
                .menusInfo(MenusInfo.builder().description(this.menuDescription).name(this.menuName).price(Double.parseDouble(this.menuPrice)).build())
                .availablePeriod(this.dateRange)
                .visitRate(new VisitRate(0.0, 0.0))
                .build();
    }
}
