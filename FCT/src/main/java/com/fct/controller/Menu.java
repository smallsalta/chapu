package com.fct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Menu 
{
	@RequestMapping("menu")
	public ModelAndView cargarPantalla()
	{
		return new ModelAndView("menu");
	}
}
