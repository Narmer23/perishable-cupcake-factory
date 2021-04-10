package it.project.cupcakefactory.service;

import it.project.cupcakefactory.dto.BatchDto;
import it.project.cupcakefactory.entity.Batch;

import java.util.Date;
import java.util.List;

public interface BatchService {

    List<BatchDto> getBatches(Date from, Date to, boolean includeExpired);

    List<BatchDto> getAllBatches(Date calculateExpireFrom);

    BatchDto processBatch(Date target, Batch batch);

    Date getExpireDate(Date from);
}
