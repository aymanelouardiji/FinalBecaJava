package com.ntt.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.finalProject.model.Customers;
import com.ntt.finalProject.model.User;
import com.ntt.finalProject.model.UserRegistrationRequest;
import com.ntt.finalProject.service.UserService;

@Controller
@RequestMapping("users")
public class UserPageController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="")
	public String index() {
		return "redirect:/users/1";
	}
	
	@GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<User> page = userService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listUsers", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "users/listUser";

    }
	
	@GetMapping("/add")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationRequest", new UserRegistrationRequest());
        return "users/registerUser";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("userRegistrationRequest") UserRegistrationRequest request) {
        userService.registerUser(request);
        return "redirect:/users"; // Redirect to login page after successful registration
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        userService.delete(id);
        return "redirect:/users";

    }
	
}
