package pl.sdacademy.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {

    private String firstName;

    private String lastName;

    private String email;

    private String city;

    private String street;

    private String building;

    private String apartment;

    private String zipCode;

    private String phoneNumber;
}
