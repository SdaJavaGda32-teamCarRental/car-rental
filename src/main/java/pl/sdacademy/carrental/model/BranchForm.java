package pl.sdacademy.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.carrental.domain.BranchStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchForm {

    private String city;

    private String street;

    private String building;

    private String apartment;

    private String zipCode;

    private BranchStatus status;


}
