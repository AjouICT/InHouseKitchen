package com.ajouict.inhousekitchen.domain;

import com.ajouict.inhousekitchen.dto.HostDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Host {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
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
    private MenusInfo menusInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User myself;

    private double avg_score;

    private int total_guest_number;

    @ElementCollection
    @CollectionTable(
            name = "MENU_IMAGES",
            joinColumns = @JoinColumn(name = "HOST_ID")
    )
    private Set<MenuImage> menuImages = new HashSet<>();

    private String availablePeriod;

    public Host(){}

    @Builder
    public Host(String introduction, @NotNull String contact_info,  Location location, MenusInfo menusInfo) {
        this.introduction = introduction;
        this.contact_info = contact_info;
        this.location = location;
        this.menusInfo = menusInfo;
    }

    public static Host registerUserInfo(User myself, Host host){
        host.myself = myself;
        myself.registerHostInfo(host);
        return host;
    }

    public Set<MenuImage> recordMenuImageInfo(MenuImage menuImage){
        this.menuImages.add(menuImage);
        return menuImages;
    }

    public HostDto _toHostDto() {
        return HostDto.builder()
                .introduction(this.introduction)
                .contact_info(this.contact_info)
                .menu_name(this.menusInfo.getName())
                .menu_price(this.menusInfo.getPrice())
                .menu_description(this.menusInfo.getDescription())
                .date_range(this.availablePeriod)
                .location_latitude(this.location.getLatitude())
                .location_longitude(this.location.getLongitude()).build();
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
