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
    private String name;
    @Column(nullable=false)
    private String password;
    private String nationality;
    private String phoneNum;
    private String email;
    @Lob
    private String intro;
    private Boolean isHost=Boolean.FALSE;

    public User() {
    }

    public User(String userId, String name, String password, String nationality, String phoneNum, String email, String intro, Boolean isHost) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
        this.email = email;
        this.intro = intro;
        this.isHost = isHost;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nationality='" + nationality + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", intro='" + intro + '\'' +
                ", isHost=" + isHost +
                '}';
    }
}
