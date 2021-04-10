package it.project.cupcakefactory.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.entity.Batch;
import it.project.cupcakefactory.repository.BatchRepository;
import it.project.cupcakefactory.views.BatchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/rest/batches")
public class BatchCtrl {

    @Autowired
    private BatchRepository batchRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @JsonView(BatchView.class)
    public Collection<Batch> getBatches() {
        return batchRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(BatchView.class)
    public Batch getBatch(@PathVariable UUID id) {
        return batchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Batch Not Found"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @JsonView(BatchView.class)
    public Batch addBatch(@RequestBody Batch batch) {
        return batchRepository.save(batch);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @JsonView(BatchView.class)
    public Batch modifyBatch(@RequestBody Batch batch) {
        if (batch.getId() == null || !batchRepository.existsById(batch.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Batch is new or doesn't exist");
        return batchRepository.save(batch);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBatch(@PathVariable UUID id) {
        batchRepository.deleteById(id);
    }
}
