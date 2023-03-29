package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    private String orderId;

    private Date date;

    private double Total;

    @OneToMany(mappedBy = "orders", targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;

}
