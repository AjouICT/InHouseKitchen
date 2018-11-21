package com.ajouict.inhousekitchen.domain;

import com.ajouict.inhousekitchen.dto.HostDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Host {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String introduction;

    @NotNull
    private String contactInfo;

    @Column(nullable = true)
    private int maxGuest;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="latitude",
                    column=@Column(name="LOCATION_LATITUDE")),
            @AttributeOverride(name="longitude",
                    column=@Column(name="LOCATION_LONGITUDE"))
    })
    private Location location;

    @Column(nullable = true)
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

    @Column(nullable = true)
    private double avgScore;

    @Column(nullable = true)
    private int totalGuestNumber=0;

    private int totalScore=0;


    @Column(nullable = true)
    @ElementCollection
    @CollectionTable(
            name = "MENU_IMAGES",
            joinColumns = @JoinColumn(name = "HOST_ID")
    )
    private Set<MenuImage> menuImages = new HashSet<>();

    @OneToMany(mappedBy = "host")
    private List<Review> reviews;



    private String availablePeriod;

    public Host(){}

    @Builder
    public Host(String introduction, @NotNull String contactInfo,  Location location, MenusInfo menusInfo, String availablePeriod) {
        this.introduction = introduction;
        this.contactInfo = contactInfo;
        this.location = location;
        this.menusInfo = menusInfo;
        this.availablePeriod = availablePeriod;
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
                .contact_info(this.contactInfo)
                .menu_name(this.menusInfo.getName())
                .menu_price(Double.toString(this.menusInfo.getPrice()))
                .menu_description(this.menusInfo.getDescription())
                .date_range(this.availablePeriod)
                .location_latitude(Double.toString(this.location.getLatitude()))
                .location_longitude(Double.toString(this.location.getLongitude())).build();
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

    public void calculateScore(int score, int flag){

        // 추가
        if (flag == 1){
            this.totalScore += score; // 점수 증가
            this.totalGuestNumber += 1; // 사람 증가
        }
        // 수정
        else if (flag == 2){
            this.totalScore += score;
        }
        // 삭제
        else{
            System.out.println("삭제 전 인원 수 : " + this.totalGuestNumber);
            System.out.println("삭제 전 평점 수 : " + this.totalScore);
            this.totalScore -= score;
            this.totalGuestNumber--;
            System.out.println(" 인원 수 : " + this.totalGuestNumber);
            System.out.println(" 평점 수 : " + this.totalScore);
        }
        this.avgScore = (float)this.totalScore / this.totalGuestNumber;
        // 소수점 첫째자리까지만 저장
        this.avgScore = (int)(this.avgScore * 10);
        this.avgScore = this.avgScore / 10;
    }

    public boolean IsSameHost(User loginUser){
        if(loginUser == null){
            return false;
        }
        return this.id.equals(loginUser.getId());
    }

}
