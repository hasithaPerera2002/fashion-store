package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private String id;
    @Column(name = "name",length = 45)
    private String name;
    @Column(length = 45)
    private String address;
    @Column(length = 45)
    private String telNo;

    @OneToMany(targetEntity = Orders.class,mappedBy = "customer")
    @ToString.Exclude
    private List<Orders>list;
}
