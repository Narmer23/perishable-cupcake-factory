package it.project.cupcakefactory.rest.controller;

import it.project.cupcakefactory.entity.UoM;
import it.project.cupcakefactory.repository.UomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/rest/uoms")
public class UomCtrl {

    @Autowired
    private UomRepository uomRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<UoM> getUoms() {
        return uomRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UoM getUom(@PathVariable UUID id) {
        return uomRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Uom Not Found"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UoM addUom(@RequestBody UoM uom) {
        return uomRepository.save(uom);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public UoM modifyUom(@RequestBody UoM uom) {
        if (uom.getId() == null || !uomRepository.existsById(uom.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Uom is new or doesn't exist");
        return uomRepository.save(uom);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUom(@PathVariable UUID id) {
        uomRepository.deleteById(id);
    }
}
