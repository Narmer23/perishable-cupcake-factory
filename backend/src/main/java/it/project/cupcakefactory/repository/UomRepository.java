package it.project.cupcakefactory.repository;

import it.project.cupcakefactory.entity.UoM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface UomRepository extends CrudRepository<UoM, UUID> {
    Collection<UoM> findAll();
}
