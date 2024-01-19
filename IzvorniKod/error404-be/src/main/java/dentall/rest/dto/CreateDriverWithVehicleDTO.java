package dentall.rest.dto;

import dentall.domain.Vehicle;

public class CreateDriverWithVehicleDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Vehicle vehicle;

    private String workStartTime;

    private String workingDays;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getWorkStartTime() {
        return workStartTime;
    }

    public String getWorkingDays() {
        return workingDays;
    }
}
