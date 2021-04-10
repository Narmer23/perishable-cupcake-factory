package it.project.cupcakefactory.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.entity.Cake;
import it.project.cupcakefactory.repository.CakeRepository;
import it.project.cupcakefactory.views.CakeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/rest/cakes")
public class CakeCtrl {

    @Autowired
    private CakeRepository cakeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @JsonView(CakeView.class)
    public Collection<Cake> getCakes() {
        return cakeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(CakeView.class)
    public Cake getCake(@PathVariable UUID id) {
        return cakeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cake Not Found"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @JsonView(CakeView.class)
    public Cake addCake(@RequestBody Cake cake) {
        cake.getIngredients().forEach(i -> i.setCake(cake));
        return cakeRepository.save(cake);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @JsonView(CakeView.class)
    public Cake modifyCake(@RequestBody Cake cake) {
        if (cake.getId() == null || !cakeRepository.existsById(cake.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cake is new or doesn't exist");
        return cakeRepository.save(cake);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCake(@PathVariable UUID id) {
        cakeRepository.deleteById(id);
    }
}
