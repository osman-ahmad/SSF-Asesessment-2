package ibf2022ssf.submission.ecommerce.model;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Order implements Serializable {

    private String item;

    @NotNull(message="Quantity cannot be null")
    @Min(value=1, message="Quantity cannot be less than 1")
    private Integer quantity;

    private String id;

    private Integer total;

    public Order() {
    }

    

    public Order(String item,
            @NotNull(message = "Quantity cannot be null") @Min(value = 1, message = "Quantity cannot be less than 1") Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
