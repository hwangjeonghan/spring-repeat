package com.test.springrepeat.user.model;

import java.time.LocalDateTime;

public class UserDTO {

    private Integer id;
    private String userName;
    private Integer userAge;
    private String addressPost;
    private String addressDefault;
    private String addressDetail;

    public UserDTO() {
    }

    public UserDTO(Integer id, String userName, Integer userAge, String addressPost,
                   String addressDefault, String addressDetail) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.addressPost = addressPost;
        this.addressDefault = addressDefault;
        this.addressDetail = addressDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public String getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(String addressDefault) {
        this.addressDefault = addressDefault;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", addressPost='" + addressPost + '\'' +
                ", addressDefault='" + addressDefault + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }
}