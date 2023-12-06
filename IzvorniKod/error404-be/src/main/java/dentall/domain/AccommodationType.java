package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "TIP_SMJESTAJA")
public class AccommodationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Long typeId;

    @NotNull
    @Column(name = "tip_ime")
    @Size(max = 32)
    private String typeName;

    @NotNull
    @Column(name = "tip_velicina")
    private Integer typeSize;

    @NotNull
    @Column(name = "tip_br_kreveta")
    private Integer typeBedNumber;

    public AccommodationType() {
        this.typeName = null;
        this.typeSize = null;
        this.typeBedNumber = null;
    }

    public AccommodationType(String typeName, Integer typeSize, Integer typeBedNumber) {
        this.typeName = typeName;
        this.typeSize = typeSize;
        this.typeBedNumber = typeBedNumber;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(Integer typeSize) {
        this.typeSize = typeSize;
    }

    public Integer getTypeBedNumber() {
        return typeBedNumber;
    }

    public void setTypeBedNumber(Integer typeBedNumber) {
        this.typeBedNumber = typeBedNumber;
    }
}
