package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "ADRESA")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "adresa_id")
    private Long addressId;

    @NotNull
    @Column(name = "mjesto")
    private String city;

    @NotNull
    @Column(name = "ulica")
    private String street;

    @NotNull
    @Column(name = "broj")
    private Integer number;
}
