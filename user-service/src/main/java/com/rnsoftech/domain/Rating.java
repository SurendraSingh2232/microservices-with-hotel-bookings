package com.rnsoftech.domain;/*
 * @Created 23/04/2024 - 06:28
 * @User ${"PRAVENDRA KUMAR"}
 */
public class Rating {

    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;

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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public static class Builder {
        private Long ratingId;
        private Long userId;
        private Long hotelId;
        private int rating;
        private String feedback;
        private Hotel hotel;

        public Rating.Builder ratingId(Long ratingId) {
            this.ratingId = ratingId;
            return this;
        }

        public Rating.Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Rating.Builder hotelId(Long hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        public Rating.Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Rating.Builder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public Rating.Builder hotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }


        public Rating build() {
            Rating rating1 = new Rating();
            rating1.ratingId = this.ratingId;
            rating1.userId = this.userId;
            rating1.hotelId = this.hotelId;
            rating1.rating = this.rating;
            rating1.feedback = this.feedback;
            rating1.hotel = this.hotel;
            return rating1;
        }
    }

    // Static builder method
    public static Rating.Builder builder() {
        return new Builder();
    }
}
