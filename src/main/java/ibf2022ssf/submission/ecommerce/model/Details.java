package ibf2022ssf.submission.ecommerce.model;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Details implements Serializable {
    
    @NotNull(message="Name cannot be empty")
    @Size(min=2,message = "Name must be atleast 3 chars")
    private String name;

    @NotEmpty(message="Address cannot be empty")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
