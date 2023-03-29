package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_")
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stockId")
    private Stock stock;
    private int qty;
    private double total;

}
