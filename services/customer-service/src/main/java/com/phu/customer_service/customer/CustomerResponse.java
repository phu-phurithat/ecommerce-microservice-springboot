package com.phu.customer_service.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
