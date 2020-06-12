package pl.sdacademy.carrental.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "company")
public class Company {
    private static Company INSTANCE;

    @Id
    @NotNull
    @Column(name = "company_name")
    private String name;

    @NotNull
    @Column(name = "company_domain")
    private String domain;


    //Adres w Company - może dodać po prostu te pola, a nie jako odniesienie do Address? Bo OneToOne nie może być
    //jednocześnie kiedy jest Address w Branch
//    @NotNull
//    @OneToOne
//    @Column(name = "company_address")
//    private Address address;

    @Column(name = "company_street")
    private String street;

    @Column(name = "company_building")
    private String building;

    @Column(name = "company_apartment")
    private String apartment;

    @Column(name = "company_zip_code")
    private String zip;

    @Column(name = "company_city")
    private String city;



    @Column(name = "company_logotype")
    private String logotype;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @JsonIgnore
//    @OneToMany(mappedBy = "company")
//    private List<Branch> branches = new ArrayList<>();


    public static Company getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Company();
        }
        return INSTANCE;
    }
}
