package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Host {

    @Id @GeneratedValue
    private Long id;

    private String introduction;
    @NotNull
    private String contact_info;

    private int max_guest;

    @Embedded
    private Location location;

    @Embedded
    private VisitRate visitRate;

    @Embedded
    private Menus menus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User myself;

    private boolean isAvailableNow;

    private double avg_score;

    private int total_guest_number;

    public Host(String introduction, @NotNull String contact_info,  Location location,  Menus menus, User myself, boolean isAvailableNow) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.location = location;
        this.menus = menus;
        this.myself = myself;
        this.isAvailableNow = isAvailableNow;
    }

    public Host(String introduction, String contact_info, Menus menus, User myself, boolean isAvailableNow) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.menus = menus;
        this.myself = myself;
        this.isAvailableNow = isAvailableNow;
    }

    public HostDto _toHostDto(){
        return new HostDto(this.introduction, this.contact_info, this.menus, this.myself, this.isAvailableNow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(id, host.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
