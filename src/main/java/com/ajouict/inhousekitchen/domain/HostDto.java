package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HostDto {

    private String introduction;
    private String contact_info;

    private int max_guest;

    private Location location;

    private VisitRate visitRate;

    private Menus menus;

    private User myself;

    private boolean isAvailableNow;

    public HostDto(String introduction, String contact_info, Menus menus, User myself, boolean isAvailableNow) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.menus = menus;
        this.myself = myself;
        this.isAvailableNow = isAvailableNow;
    }

    public Host _toHost(){
        return new Host(this.introduction, this.contact_info, this.menus, this.myself, this.isAvailableNow);
    }
}
