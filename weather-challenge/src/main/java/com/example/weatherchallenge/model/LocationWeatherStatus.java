package com.example.weatherchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "location_weather_status")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class LocationWeatherStatus implements Serializable {
    public LocationWeatherStatus() {}

    public LocationWeatherStatus(@NotBlank String name, @NotBlank String title, @NotBlank String temperature, @NotBlank String currentWeather, @NotBlank Date weatherStatusDate, @NotBlank Location location) {
        this.name = name;
        this.title = title;
        this.temperature = temperature;
        this.currentWeather = currentWeather;
        this.weatherStatusDate = weatherStatusDate;
        this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String title;

    @NotBlank
    private String temperature;

    @NotBlank
    private String currentWeather;

    private Date weatherStatusDate;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Location location;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @PreUpdate
    public void setLastUpdate() {  this.updatedAt = new Timestamp(new Date().getTime()); }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public String getCurrentWeather() {
        return this.currentWeather;
    }

    public Date getWeatherStatusDate() {
        return this.weatherStatusDate;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void setWeatherStatusDate(Date weatherStatusDate) {
        this.weatherStatusDate = weatherStatusDate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
