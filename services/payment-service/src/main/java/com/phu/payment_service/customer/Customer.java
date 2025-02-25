package com.phu.payment_service.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;
    @NotNull(message = "Customer firstname is required")
    private String firstName;
    @NotNull(message = "Customer lastname is required")
    private String lastName;
    @NotNull(message = "Customer email is required")
    @Email(message = "Customer email is not correctly formatted")
    private String email;

}
