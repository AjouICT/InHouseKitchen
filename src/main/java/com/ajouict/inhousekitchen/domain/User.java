package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20, unique=true)
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
    private Boolean isHost=Boolean.FALSE;
    private String profilePhoto;


    public User() {
    }

    public User(String userId, String password, String name, String nationality, String phoneNum, String email, String intro, Boolean isHost, String profilePhoto) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
        this.email = email;
        this.intro = intro;
        this.isHost = isHost;
        this.profilePhoto = profilePhoto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getIntro() {
        return intro;
    }

    public Boolean getHost() {
        return isHost;
    }

    public String getProfilePhoto() {
        return profilePhoto;
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
                ", isHost=" + isHost +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
