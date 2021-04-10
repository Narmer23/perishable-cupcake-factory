package it.project.cupcakefactory.repository;

import it.project.cupcakefactory.entity.Batch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface BatchRepository extends CrudRepository<Batch, UUID> {
    Collection<Batch> findAll();
}
