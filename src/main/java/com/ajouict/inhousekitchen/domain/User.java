package com.ajouict.inhousekitchen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
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
    private String profilePhoto;

    @JsonIgnore
    @OneToOne(mappedBy = "myself", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Host host;


   public User() {
    }

    public User(String userId, String password, String name, String nationality, String phoneNum, String email, String intro, String profilePhoto) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nationality = nationality;
        this.phoneNum = phoneNum;
        this.email = email;
        this.intro = intro;
        this.profilePhoto = profilePhoto;
    }

    public void registerHostInfo(Host host){
        this.host = host;
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

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public Host getHost() {
        return host;
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
                ", profilePhoto='" + profilePhoto + '\'' +
                ", host=" + host +
                '}';
    }
}
