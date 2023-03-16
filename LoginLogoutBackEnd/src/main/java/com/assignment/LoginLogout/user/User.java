package com.assignment.LoginLogout.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

//@Entity allows class to be serialized and deserialized to and from JSON, allowing to create table in MySql DB
@Entity
//tells program to call the table "users"
@Table(name ="users")
@Getter @Setter @NoArgsConstructor
public class User {
    //@Id sets id variable as id field in database, @GeneratedValue tells prog to generate id value when new record
    //added, so no worry on accidentally overriding records in DB
    private @Id @GeneratedValue long id;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    public User(@NotBlank String username, @NotBlank String password){
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, username, password, loggedIn);
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }

}
