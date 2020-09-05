package com.game.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game extends BaseEntity
{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "time_limit")
    private Integer timeLimit;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "url")
    private String url;
    @Column(name ="public")
    private Boolean isPublic;
    @Column(name ="start_time")
    private Date startTime;
    @Column(name ="end_time")
    private Date end_time;
    @ManyToMany(mappedBy = "games" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<User> users;
//    @JsonBackReference // fix infinite loop ( or using Dto )
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    //@JsonManagedReference
    @OneToMany(mappedBy = "game" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Rank> ranks;
    //@JsonManagedReference
    @OneToMany(mappedBy = "game" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Image> images;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(List<Rank> ranks) {
        this.ranks = ranks;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
