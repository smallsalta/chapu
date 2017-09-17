package com.fct.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;

public class Binder 
{
	public static void doBinder(WebDataBinder binder) 
	{
		DecimalFormat decimalFormat		= new DecimalFormat();
		DecimalFormatSymbols symbols 	= new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		decimalFormat.setDecimalFormatSymbols(symbols);
		
		DateFormat dateFormat 			= new SimpleDateFormat("yyyy-MM-dd");
 
		binder.registerCustomEditor( Date.class, new CustomDateEditor(dateFormat, false) );
		binder.registerCustomEditor( Float.class, new CustomNumberEditor(Float.class, decimalFormat, true) );
		binder.registerCustomEditor( Double.class, new CustomNumberEditor(Double.class, decimalFormat, true) );
    }
}
