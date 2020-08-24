package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "web_demo", catalog = "")
public class UserEntity {
    private int iduser;
    private String username;
    private String password;

    @Id
    @Column(name = "iduser", nullable = false)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //konstruktor
    public UserEntity() {
    }



    public static class UserEntityBuilder{

        private int iduser;
        private String username;
        private String password;

        //KONSTRUKTOR
        public UserEntityBuilder(){}

        public static UserEntityBuilder anUserEntity(){
            return new UserEntityBuilder();
        }

        //WITH
        public UserEntityBuilder withIduser(int iduser){
            this.iduser = iduser;
            return this;
        }
        public UserEntityBuilder withUsername(String username){
            this.username = username;
            return this;
        }
        public UserEntityBuilder withPassword(String password){
            this.password = password;
            return this;
        }

        //BUILD
        public UserEntity build(){
            UserEntity userEntity = new UserEntity();
                userEntity.iduser = this.iduser;
                userEntity.username = this.username;
                userEntity.password = this.password;

            return userEntity;
        }

    }



                                                //EQUALS I HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return iduser == that.iduser &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, username, password);
    }









































}
