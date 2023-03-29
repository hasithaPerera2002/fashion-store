package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    private String id;
    @Column(name = "name",length = 45)
    private String name;

    private int qty;

    private double price;

    private double total;

    @OneToMany(targetEntity = OrderDetails.class,mappedBy = "stock")
    private List<OrderDetails>list;


}
