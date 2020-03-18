package com.hearc.agendarc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {
	
    @RequestMapping("/admin")
	public String admin(Model model)
	{
    	return "admin";
	}

}
