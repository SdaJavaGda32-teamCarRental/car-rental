package pl.sdacademy.carrental.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ClientForm {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String building;

    private String apartment;

    @NotBlank
    private String zipCode;

    @Column(name = "phone_number")
    @NotNull
    @Digits(integer = 9, fraction = 0, message = "Phone number must consist of 9 digits")
    private String phoneNumber;
}
