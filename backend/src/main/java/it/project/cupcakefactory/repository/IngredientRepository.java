package it.project.cupcakefactory.repository;

import it.project.cupcakefactory.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {
    Collection<Ingredient> findAll();
}
