package it.project.cupcakefactory.repository;

import it.project.cupcakefactory.entity.CakeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CakeIngredientRepository extends CrudRepository<CakeIngredient, UUID> {
    Collection<CakeIngredient> findAll();
}
