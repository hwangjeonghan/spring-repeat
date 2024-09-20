package com.test.springrepeat.user.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;

    @Column(name = "user_name")
    private final String userName;

    @Column(name = "user_age")
    private final Integer userAge;

    @Column(name = "user_ageaddress_post")
    private final String addressPost;

    @Column(name = "user_address_default")
    private final String addressDefault;

    @Column(name = "user_address_detail")
    private final String addressDetail;

    @Column(name = "user_create_at")
    @CreationTimestamp
    private final LocalDateTime userCreateAt;

    @Column(name = "user_update_at")
    @UpdateTimestamp
    private final LocalDateTime userUpdateAt;

    protected UserEntity() {
        this.id = null;
        this.userName = null;
        this.userAge = null;
        this.addressPost = null;
        this.addressDefault = null;
        this.addressDetail = null;
        this.userCreateAt = null;
        this.userUpdateAt = null;
    }

    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.userAge = builder.userAge;
        this.addressPost = builder.addressPost;
        this.addressDefault = builder.addressDefault;
        this.addressDetail = builder.addressDetail;
        this.userCreateAt = builder.userCreateAt;
        this.userUpdateAt = builder.userUpdateAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String userName;
        private Integer userAge;
        private String addressPost;
        private String addressDefault;
        private String addressDetail;
        private LocalDateTime userCreateAt;
        private LocalDateTime userUpdateAt;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            if (userName == null || !userName.matches("^[가-힣]{3}$")) {
                throw new IllegalArgumentException("이름은 한글로만 이루어져야하며 3글자 합니다.");
            }
            this.userName = userName;
            return this;
        }

        public Builder userAge(Integer userAge) {
            if (userAge < 20) {
                throw new IllegalArgumentException("회원의 나이는 20살 이상이여야 합니다.");
            }
            this.userAge = userAge;
            return this;
        }

        public Builder addressPost(String addressPost) {
            if (addressPost == null || !addressPost.matches("\\d{5}")) {
                throw new IllegalArgumentException("우편번호는 5자리 숫자여야 합니다.");
            }
            this.addressPost = addressPost;
            return this;
        }

        public Builder addressDefault(String addressDefault) {
            if(addressDefault==null){
                throw new IllegalArgumentException("기본 주소는 생략 할 수 없습니다.");
            }
            this.addressDefault = addressDefault;
            return this;
        }

        public Builder addressDetail(String addressDetail) {
            if(addressDefault==null){
                throw new IllegalArgumentException("기본 주소는 삭제 할 수 없습니다.");
            }
            this.addressDetail = addressDetail;
            return this;
        }

        public Builder userCreateAt(LocalDateTime userCreateAt) {
            this.userCreateAt = userCreateAt;
            return this;
        }

        public Builder userUpdateAt(LocalDateTime userUpdateAt) {
            this.userUpdateAt = userUpdateAt;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", addressPost='" + addressPost + '\'' +
                ", addressDefault='" + addressDefault + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", userCreateAt=" + userCreateAt +
                ", userUpdateAt=" + userUpdateAt +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddressDefault() {
        return addressDefault;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public LocalDateTime getUserCreateAt() {
        return userCreateAt;
    }

    public LocalDateTime getUserUpdateAt() {
        return userUpdateAt;
    }
}