package com.ajay.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

         String id,
         @NotNull(message = "Customer First Name Req")
         String firstName,
         @NotNull(message = "Customer Last Name Req")
         String lastName,
         @NotNull(message = "Customer Email Req*")
         @Email(message = "Customer email is not valid")
         String email,

         Address address
) {
}
