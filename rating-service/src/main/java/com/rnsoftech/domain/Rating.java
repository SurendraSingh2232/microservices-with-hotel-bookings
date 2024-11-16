package com.rnsoftech.domain;/*
 * @Created 24/04/2024 - 09:44
 * @User ${"PRAVENDRA KUMAR"}
 */


import javax.persistence.*;

@Entity
@Table(name = "RATING")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RATING_ID")
    private Long ratingId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "HOTEL_ID")
    private Long hotelId;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "FEEDBACK")
    private String feedback;

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
