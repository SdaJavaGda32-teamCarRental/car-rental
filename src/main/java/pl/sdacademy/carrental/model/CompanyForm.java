package pl.sdacademy.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyForm {

    private String name;

    private String domain;

    private String street;

    private String building;

    private String apartment;

    private String zipCode;

    private String city;

    private Long logotypeId;
}
