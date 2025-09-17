package taco.cloud.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import taco.cloud.pojo.Ingredient;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ingredients")

//@SessionAttributes("ingredientInfo")
//custom class for demonstration working Auth2
public class IngredientController {

    private List<Ingredient> ingredients;

    @ModelAttribute
    private void initIngredients(Model model) {

        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        model.addAttribute("ingredients", ingredients);
    }

    @GetMapping
    public String showList() {
        return "ingredients";
    }

    @GetMapping("/api/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
