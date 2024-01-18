package dentall.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "VOZILO")
public class Vehicle {
    @Id
    @NotNull
    @Column(name = "reg_vozila")
    @Size(min=7, max = 8)
    private String registration;

    @NotNull
    @Column(name = "model")
    @Size(max = 32)
    private String model;

    @NotNull
    @Column(name = "boja")
    @Size(max = 32)
    private String color;

    @NotNull
    @Column(name = "kapacitet")
    private Integer capacity;

    public Vehicle(String registration, String model, String color, Integer capacity) {
        this.registration = registration;
        this.model = model;
        this.color = color;
        this.capacity = capacity;
    }

    public Vehicle() {
        this.registration = null;
        this.model = null;
        this.color = null;
        this.capacity = null;
    }

    public String getRegistration() {
        return registration;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registration='" + registration + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
