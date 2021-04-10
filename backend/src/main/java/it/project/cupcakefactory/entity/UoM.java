package it.project.cupcakefactory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.project.cupcakefactory.util.RootEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class UoM extends RootEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    private String name;
    private String label;

    @OneToMany(mappedBy = "uoM")
    private Set<CakeIngredient> cakeIngredients = new HashSet<>();

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonIgnore
    public Set<CakeIngredient> getCakeIngredients() {
        return cakeIngredients;
    }

    public void setCakeIngredients(Set<CakeIngredient> cakeIngredients) {
        this.cakeIngredients = cakeIngredients;
    }
}
