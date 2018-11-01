package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "host_id"))
    private User host;

    @Lob
    private String hostIntro;
    @Column(nullable=false)
    private String hostContact;

    @Column(nullable=false)
    private int maxGuest;

    @Column(nullable=false)
    private LocalDateTime startDate;

    @Column(nullable=false)
    private LocalDateTime endDate;

    @Column(nullable=false)
    private double latitude;

    @Column(nullable=false)
    private double longitude;
    //private String hostPic; 이것은 유저 객체에 이미 프로필이 있기 때문에 생략
    private int visitTwice;
    private int visitTripple;
    private float avgScore;
    private int totalGuest;

    @Column(nullable=false)
    private String menuName;

    @Column(nullable=false)
    private String menuPrice;

    @Lob
    @Column(nullable=false)
    private String menuIntro;

    @OneToMany(mappedBy = "host")
    private List<Review> reviews;

    public Host(User host, String hostIntro, String hostContact, int maxGuest, LocalDateTime startDate, LocalDateTime endDate, double latitude, double longitude, int visitTwice, int visitTripple, float avgScore, int totalGuest, String menuName, String menuPrice, String menuIntro) {
        this.host = host;
        this.hostIntro = hostIntro;
        this.hostContact = hostContact;
        this.maxGuest = maxGuest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitTwice = visitTwice;
        this.visitTripple = visitTripple;
        this.avgScore = avgScore;
        this.totalGuest = totalGuest;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuIntro = menuIntro;
    }

    public Host() {
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public String getHostIntro() {
        return hostIntro;
    }

    public void setHostIntro(String hostIntro) {
        this.hostIntro = hostIntro;
    }

    public String getHostContact() {
        return hostContact;
    }

    public void setHostContact(String hostContact) {
        this.hostContact = hostContact;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getVisitTwice() {
        return visitTwice;
    }

    public void setVisitTwice(int visitTwice) {
        this.visitTwice = visitTwice;
    }

    public int getVisitTripple() {
        return visitTripple;
    }

    public void setVisitTripple(int visitTripple) {
        this.visitTripple = visitTripple;
    }

    public float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(float avgScore) {
        this.avgScore = avgScore;
    }

    public int getTotalGuest() {
        return totalGuest;
    }

    public void setTotalGuest(int totalGuest) {
        this.totalGuest = totalGuest;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuIntro() {
        return menuIntro;
    }

    public void setMenuIntro(String menuIntro) {
        this.menuIntro = menuIntro;
    }

    public boolean IsSameHost(User loginUser){
        if(loginUser == null){
            return false;
        }
        return this.id.equals(loginUser.getId());
    }

    @Override
    public String toString() {
        return "Host{" +
                "id=" + id +
                ", host=" + host +
                ", hostIntro='" + hostIntro + '\'' +
                ", hostContact='" + hostContact + '\'' +
                ", maxGuest=" + maxGuest +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", visitTwice=" + visitTwice +
                ", visitTripple=" + visitTripple +
                ", avgScore=" + avgScore +
                ", totalGuest=" + totalGuest +
                ", menuName='" + menuName + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuIntro='" + menuIntro + '\'' +
                '}';
    }
}
