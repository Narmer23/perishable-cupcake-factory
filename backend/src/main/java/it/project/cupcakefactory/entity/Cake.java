package it.project.cupcakefactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.util.RootEntity;
import it.project.cupcakefactory.views.BatchView;
import it.project.cupcakefactory.views.CakeView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Cake extends RootEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private String name;

    private BigDecimal defaultPrice;

    @OneToMany(mappedBy = "cake", cascade = CascadeType.ALL)
    private Set<CakeIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "cake", cascade = CascadeType.ALL)
    private Set<Batch> batches = new HashSet<>();

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView({CakeView.class, BatchView.class})
    public Set<CakeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<CakeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @JsonIgnore
    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
