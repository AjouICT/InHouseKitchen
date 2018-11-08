package com.ajouict.inhousekitchen.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Host {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @JsonProperty(value = "introduction")
    private String introduction;

    @NotNull
    private String contact_info;

    private int max_guest;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="latitude",
                    column=@Column(name="LOCATION_LATITUDE")),
            @AttributeOverride(name="longitude",
                    column=@Column(name="LOCATION_LOGITUDE"))
    })
    private Location location;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="second",
                    column=@Column(name="VISITRATE_SECOND")),
            @AttributeOverride(name="third",
                    column=@Column(name="VISITRATE_THIRD"))
    })
    private VisitRate visitRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name",
                    column=@Column(name="MENU_NAME")),
            @AttributeOverride(name="price",
                    column=@Column(name="MENU_PRICE")),
            @AttributeOverride(name="description",
                    column=@Column(name="MENU_DESCRIPTION")
            )
    })
    private Menus menus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User myself;

    private double avg_score;

    private int total_guest_number;

    @ElementCollection
    @CollectionTable(
            name = "MENU_IMAGE",
            joinColumns = @JoinColumn(name = "HOST_ID")
    )
    private Set<MenuImage> menuImages = new HashSet<>();

    public Host(){}

    public Host(String introduction, @NotNull String contact_info,  Location location, Menus menus) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.location = location;
        this.menus = menus;
    }

    public void registerUserInfo(User myself){
        this.myself = myself;
        myself.registerHostInfo(this);
    }

    public Set<MenuImage> registerMenuImage(MenuImage menuImage){
        this.menuImages.add(menuImage);
        return menuImages;
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
