package it.project.cupcakefactory.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.entity.Ingredient;
import it.project.cupcakefactory.repository.IngredientRepository;
import it.project.cupcakefactory.views.IngredientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/rest/ingredients")
public class IngredientCtrl {

    @Autowired
    private IngredientRepository ingredientRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @JsonView(IngredientView.class)
    public Iterable<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(IngredientView.class)
    public Ingredient getIngredient(@PathVariable UUID id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient Not Found"));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @JsonView(IngredientView.class)
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @JsonView(IngredientView.class)
    public Ingredient modifyIngredient(@RequestBody Ingredient ingredient) {
        if (ingredient.getId() == null || !ingredientRepository.existsById(ingredient.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingredient is new or doesn't exist");
        return ingredientRepository.save(ingredient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteIngredient(@PathVariable UUID id) {
        ingredientRepository.deleteById(id);
    }
}
