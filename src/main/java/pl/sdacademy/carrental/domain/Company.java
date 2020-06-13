package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

   @NotNull
   @OneToOne
   @JoinColumn(name = "address_id")
   private Address address;

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
