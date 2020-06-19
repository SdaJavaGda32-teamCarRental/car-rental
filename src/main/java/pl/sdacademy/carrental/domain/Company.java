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

   @OneToOne
   @JoinColumn(name = "logotype_id")
   private Logotype logotype;
}
