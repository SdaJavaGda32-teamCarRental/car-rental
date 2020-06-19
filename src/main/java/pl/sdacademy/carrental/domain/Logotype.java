package pl.sdacademy.carrental.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "logotypes")
public class Logotype {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logotype_id_seq")
    @SequenceGenerator(name = "logotype_id_seq", sequenceName = "logotype_id_seq", allocationSize = 50, initialValue = 1)
    private Long id;

    @Column(name = "data")
    @Lob
    private byte[] image;
}
