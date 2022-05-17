package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		return "addIngredient";
	}
	
	@PostMapping
	public String addIngredient(
		@RequestParam("id") String id,
		@RequestParam("name") String name,
		@RequestParam("type") String type,
		Model model
	) {
		Ingredient ingredient = new Ingredient(
			id,
			name,
			Type.valueOf(type)
		);
		rest.postForObject("http://localhost:8081/ingredients", ingredient, Ingredient.class);
		
		model.addAttribute(ingredient);
		return "addIngredientSuccess";
	}
}
