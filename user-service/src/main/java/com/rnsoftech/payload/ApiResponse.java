package com.rnsoftech.payload;/*
 * @Created 22/04/2024 - 22:20
 * @User ${"PRAVENDRA KUMAR"}
 */

import org.springframework.http.HttpStatus;
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

    private ApiResponse() {}

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static class Builder {
        private String message;
        private boolean success;
        private HttpStatus status;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponse build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.message = this.message;
            apiResponse.success = this.success;
            apiResponse.status = this.status;
            return apiResponse;
        }
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }
}
