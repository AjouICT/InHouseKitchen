package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="mem_idx")
    private Long id;

    @Column(nullable=false,unique=true)
    private String userId;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String name;
    private String nationality;
    private String phoneNum;
    private String email;
    @Lob
    private String intro;
    @Column(nullable=true)
    private double latitude;
    @Column(nullable=true)
    private double longitude;
    private Boolean isHost=Boolean.FALSE;
    private String profilePhoto;

    @OneToOne(mappedBy = "host")
    private Host host;


    public User() {
    }

    public User(String userId, String password, String name, String nationality, String phoneNum, String email, String intro, double latitude, double longitude, Boolean isHost, String profilePhoto) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
        this.email = email;
        this.intro = intro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isHost = isHost;
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public Boolean getHost() {
        return isHost;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean matchId(Long newId){
        if(newId==null){
            return false;
        }

        return newId.equals(userId);
    }

    public boolean matchPassword(String newPassword){
        if(newPassword==null){
            return false;
        }

        return newPassword.equals(password);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", intro='" + intro + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isHost=" + isHost +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
