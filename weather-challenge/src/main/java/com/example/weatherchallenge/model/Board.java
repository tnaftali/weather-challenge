//package com.example.weatherchallenge.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "boards")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
//public class Board implements Serializable {
//    public Board(@NotBlank String name, @NotBlank User user) {
//        this.name = name;
//        this.user = user;
//        this.createdAt = new Date();
//        this.updatedAt = new Date();
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    private String name;
//
//    @OneToMany(targetEntity=Location.class, mappedBy="board", fetch=FetchType.EAGER)
//    private List<Location> locations;
//
//    @NotBlank
//    private User user;
//
//    @Column(nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;
//}
package com.example.weatherchallenge.model;

import com.example.weatherchallenge.model.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "boards")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Board implements Serializable {

    protected Board() {
    }

    public Board(@NotBlank String name, @NotBlank User user) {
        this.name = name;
        this.user = user;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "board_location",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    public List<Location> locations = new ArrayList<>();

    public User user;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    private String getUsername() {
        return user.getUsername();
    }

    public BoardDto toDto() {
        return new BoardDto(name, getUsername());
    }
}
