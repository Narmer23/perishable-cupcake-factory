package it.project.cupcakefactory.service.impl;

import it.project.cupcakefactory.dto.BatchDto;
import it.project.cupcakefactory.entity.Batch;
import it.project.cupcakefactory.service.BatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

    @PersistenceContext
    private EntityManager em;

    @Value("${it.project.cupcakefactory.daysToExpire}")
    private int daysToExpire;

    @Value("#{${it.project.cupcakefactory.daysPriceModifiersMap}}")
    private Map<Long, Double> daysPriceModifiersMap;

    @Override
    public List<BatchDto> getBatches(Date from, Date to, boolean includeExpired) {
        List<Batch> batches = em.createNamedQuery("Batch.findByProductionDateBetween", Batch.class)
                .setParameter("productionDateStart", from)
                .setParameter("productionDateEnd", to)
                .getResultList();

        return batches.stream()
                .map(batch -> processBatch(to, batch))
                .filter(batchDto -> includeExpired || !batchDto.isExpired())
                .collect(Collectors.toList());
    }

    @Override
    public List<BatchDto> getAllBatches(Date calculateExpireFrom) {
        return em.createNamedQuery("Batch.find", Batch.class).getResultList()
                .stream()
                .map(batch -> processBatch(calculateExpireFrom, batch))
                .collect(Collectors.toList());
    }

    @Override
    public BatchDto processBatch(Date target, Batch batch) {
        if (target.before(batch.getProductionDate()))
            return new BatchDto(batch, 1, false);

        Long daysBetween = ChronoUnit.DAYS.between(batch.getProductionDate().toInstant(), target.toInstant());

        if (daysBetween >= daysToExpire)
            return new BatchDto(batch, 0, true);

        if (daysPriceModifiersMap.containsKey(daysBetween))
            return new BatchDto(batch, daysPriceModifiersMap.get(daysBetween), false);

        throw new IllegalStateException("Cannot determine batch status");
    }

    @Override
    public Date getExpireDate(Date from) {
        LocalDateTime ldt = LocalDateTime.ofInstant(from.toInstant(), ZoneId.systemDefault()).minusDays(daysToExpire);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
