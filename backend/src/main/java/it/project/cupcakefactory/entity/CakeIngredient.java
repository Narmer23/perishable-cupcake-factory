package it.project.cupcakefactory.entity;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.util.RootEntity;
import it.project.cupcakefactory.views.BatchView;
import it.project.cupcakefactory.views.CakeView;
import it.project.cupcakefactory.views.IngredientView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class CakeIngredient extends RootEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @ManyToOne(optional = false)
    private Cake cake;
    @ManyToOne(optional = false)
    private Ingredient ingredient;
    @ManyToOne
    private UoM uoM;

    private BigDecimal quantity;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonView(IngredientView.class)
    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    @JsonView({BatchView.class, CakeView.class})
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public UoM getUoM() {
        return uoM;
    }

    public void setUoM(UoM uoM) {
        this.uoM = uoM;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
