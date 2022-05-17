package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import tacos.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		rest.postForObject("http://localhost:8081/orders", order, Order.class);
		return "redirect:/";
	}
}
