package it.project.cupcakefactory.entity;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.util.RootEntity;
import it.project.cupcakefactory.views.IngredientView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Ingredient extends RootEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Set<CakeIngredient> cakes = new HashSet<>();

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

    @JsonView(IngredientView.class)
    public Set<CakeIngredient> getCakes() {
        return cakes;
    }

    public void setCakes(Set<CakeIngredient> cakes) {
        this.cakes = cakes;
    }
}
