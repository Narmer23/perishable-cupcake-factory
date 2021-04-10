package it.project.cupcakefactory.repository;

import it.project.cupcakefactory.entity.Cake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface CakeRepository extends CrudRepository<Cake, UUID> {
    Collection<Cake> findAll();
}
