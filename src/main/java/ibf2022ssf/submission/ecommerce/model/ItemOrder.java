package ibf2022ssf.submission.ecommerce.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class ItemOrder implements Serializable{
    
    private String invoiceId;
    private String name;
    private String address;
    private Double total;

    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
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
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("invoiceId", this.getInvoiceId())
                .add("name", this.getName())
                .add("address", this.getAddress())
                .add("total", this.getTotal())
                .build();
    }

    public static ItemOrder create(String json) throws IOException{
        ItemOrder i = new ItemOrder();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            i.setInvoiceId(o.getString("orderId"));
            i.setName(o.getString("name"));
            i.setAddress(o.getString("address"));
            i.setTotal((double) o.getInt("total"));
        }
        return i;
    }

}
