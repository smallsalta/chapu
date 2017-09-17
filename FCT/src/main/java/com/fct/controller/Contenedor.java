package com.fct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Contenedor 
{
	@RequestMapping("contenedor")
	public ModelAndView cargarPantalla()
	{
		return new ModelAndView("contenedor");
	}
}
