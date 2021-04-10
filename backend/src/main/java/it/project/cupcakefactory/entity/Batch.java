package it.project.cupcakefactory.entity;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.util.RootEntity;
import it.project.cupcakefactory.views.BatchView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Batch.find", query = "SELECT b FROM Batch b"),
        @NamedQuery(name = "Batch.findByProductionDateBetween", query = "SELECT b FROM Batch b WHERE b.productionDate >= :productionDateStart AND b.productionDate <= :productionDateEnd")
})
public class Batch extends RootEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private Date productionDate;

    private BigDecimal batchPrice;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cake cake;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonView(BatchView.class)
    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public BigDecimal getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(BigDecimal batchPrice) {
        this.batchPrice = batchPrice;
    }
}
