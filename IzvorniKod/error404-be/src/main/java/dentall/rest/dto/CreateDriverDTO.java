package dentall.rest.dto;

public class CreateDriverDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String vehicleReg;

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

    public String getVehicleReg() {
        return vehicleReg;
    }

    public String getWorkStartTime() {
        return workStartTime;
    }

    public String getWorkingDays() {
        return workingDays;
    }
}
