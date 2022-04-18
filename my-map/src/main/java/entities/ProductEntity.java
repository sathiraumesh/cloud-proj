package entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name="last_tracing")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String section;

    private String rack;

    private int number;

    private String lon;

    private String lat;
}
