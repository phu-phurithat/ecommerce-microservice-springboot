package com.phu.customer_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {

        private String id;

        @NotNull(message = "Customer firstname is required")
        private String firstName;

        @NotNull(message = "Customer lastname is required")
        private String lastName;

        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is not valid email address")
        private String email;

        private Address address;


}