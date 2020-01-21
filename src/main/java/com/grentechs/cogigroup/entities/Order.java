package com.grentechs.cogigroup.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
