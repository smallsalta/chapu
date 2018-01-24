package com.fct.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fct.persistencia.TContratos;
import com.fct.persistencia.TLineasContrato;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ContratoPDFImpl 
implements ContratoPDF
{
	private TContratos contratos;
	private byte[] sello;
	private byte[] empresa;

	@Override
	public ByteArrayOutputStream createPDF() 
	throws DocumentException, IOException 
	{
		ByteArrayOutputStream out 	= new ByteArrayOutputStream();
		Document document 			= new Document( PageSize.A4, 30f, 30f, 30f, 30f );
		PdfWriter writer 			= PdfWriter.getInstance( document, out );
		ContratoHeaderFooter event	= new ContratoHeaderFooter();

		writer.setPageEvent(event);
		
		document.open();
        document.add( this.createPagina1() );
        document.newPage();
        document.add( this.createPagina2() );
//        document.newPage();
//        document.add( this.createPagina3() );
        document.newPage();
        document.add( this.createPagina4() );
		document.close();
		
		return out;
	}
	
	@Override
	public ByteArrayOutputStream createPDF(byte[] sello, TContratos c) 
	throws DocumentException, IOException 
	{
		this.sello 		= sello;
		this.contratos 	= c;
		
		return this.createPDF();
	}

	@Override
	public void setSello(byte[] sello) 
	{
		this.sello = sello;	
	}
	
	@Override
	public void setEmpresa(byte[] empresa) 
	{
		this.empresa = empresa;	
	}

	@Override
	public void setContrato(TContratos contratos) 
	{
		this.contratos = contratos;
	}
	
	private PdfPTable createPagina1() 
	throws BadElementException, IOException
	{
		PdfPTable table	= new PdfPTable(1);
		
		table.setWidthPercentage(100f);

		this.createCabecera(table);
        this.createTitulo(table);
        table.addCell( ComunPDF.createCeldaDescripcion(" ") );
        this.createCliente(table);
        table.addCell( ComunPDF.createCeldaDescripcion(" ") );
        this.createExtintores(table);
        table.addCell( ComunPDF.createCeldaDescripcion(" ") );
        this.createPie(table); 
        
        return table;
	}
	
	private PdfPTable createPagina2() 
	throws BadElementException, IOException
	{
		PdfPTable table	= new PdfPTable(1);		

		table.setWidthPercentage(100f);

		this.createCabecera(table);
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		this.createParrafoCliente(table);
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		this.createPuntosParrafo(table);
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		this.createParrafoConforme(table);
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		this.createParrafoFinal(table);
		
		return table;
	}
	
	private PdfPTable createPagina3() 
	throws BadElementException, IOException
	{
		PdfPTable table	= new PdfPTable(1);		
		PdfPCell sello 	= ComunPDF.createCeldaSello( this.sello );
		Font f			= new Font();
		
		f.setSize(10F);
		table.setWidthPercentage(100f);
		sello.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );

		this.createCabecera(table);
		
		table.addCell( ComunPDF.createCeldaTH("INSPECCIÓN VISUAL") );
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		table.addCell( ComunPDF.createCeldaDescripcion("T: trimestral, A: anual. B: bien, M: mal") );
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿La señal de cada extintor es adecuada, visible e identificable y correctamente colocada?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿El extintor es accesible y está correctamente ubicado para acceder a él en caso de fuego?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿La altura desde el suelo es correcta (máximo 1,70 m)?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿Están exentos de corrosión, golpes, abolladuras, suciedad?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿Los precintos y seguros están intactos?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿El anclaje y soporte de fijación es correcto y seguro?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿Están en buen estado la manguera, boquilla, válvula y manómetro?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿La identificación y etiquetas (fabricación, retimbrado y mantenimiento) son correctas?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿La comprobación del estado de carga del extintor mediante pesasa es correcto?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿En extintores de presión adosada, es correcto el peso de la carga del botellín?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿Está retimbrado el extintor (y botellín en extintores de presión adosada) conforme al RAP?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿El agente exterior está exento de anomalías aparentes?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿Es correcta la presión interior?", f) );
		table.addCell( ComunPDF.createCeldaDescripcion("[       ] ¿La cantidad, tipo y eficacia de los extintores es correcto conforme al uso y zona que protege?", f) );
		
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		
		table.addCell( ComunPDF.createCeldaTH("CONCLUSIÓN") );
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		
		table.addCell( ComunPDF.createCeldaDescripcion("Realizada la correspondiente revisión y comprobaciones, el sistema quedó:") );
		table.addCell( ComunPDF.createCeldaDescripcion("[  ] En correcto funcionamiento, sin anomalías.") );
		table.addCell( ComunPDF.createCeldaDescripcion("[  ] Con las anomalías indicadas en anexo y pendientes de corregir.") );
		
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		
		table.addCell( ComunPDF.createCeldaDescripcion("Periodicidad de la revisión:") );
		table.addCell( ComunPDF.createCeldaDescripcion("[  ] Trimestral") );
		table.addCell( ComunPDF.createCeldaDescripcion("[  ] Semestral") );
		table.addCell( ComunPDF.createCeldaDescripcion("[  ] Anual") );
		
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		table.addCell(sello);
		
		return table;
	}
	
	private PdfPTable createPagina4() 
	throws BadElementException, IOException
	{
		PdfPTable table	= new PdfPTable(1);		

		table.setWidthPercentage(100f);

		this.createCabecera(table);
		this.createParrafoAnexo(table);
		
		return table;
	}
	
	private void createCabecera(PdfPTable table) 
	throws BadElementException, IOException
	{
		PdfPCell cell 	= ComunPDF.createCeldaSello( this.empresa );
		
		cell.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
		
		table.addCell(cell);
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
	}
	
	private void createTitulo(PdfPTable table)
	{
		Font negrita	= new Font();
		Font rojo		= new Font();
		
		negrita.setStyle( Font.BOLD );
		negrita.setSize(20);
		
		rojo.setColor( BaseColor.RED );
		rojo.setSize(20);
		
		Phrase phrase1	= new Phrase("CERTIFICADO DE RETIMBRADO", negrita);
		Phrase phrase4	= new Phrase("CERTIFICADO", rojo);
		
		PdfPCell cell1	= new PdfPCell(phrase1);
		PdfPCell cell2	= ComunPDF.createCeldaDescripcion("RECARGA - REVISIÓN - INSTALACIÓN");
		PdfPCell cell3	= ComunPDF.createCeldaDescripcion("****************************************************************************************************");
		PdfPCell cell4	= new PdfPCell(phrase4);
		
		cell1.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		cell2.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		cell3.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		cell4.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		cell1.setBorder(0);
		cell4.setBorder(0);
		
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
	}
	
	private void createCliente(PdfPTable table)
	{
		PdfPTable t					= new PdfPTable(2);
		PdfPCell ct					= new PdfPCell();
		
		DateFormatSymbols symbols	= new DateFormatSymbols();
		String[] meses				= { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
		
		symbols.setMonths(meses);
		
		DateFormat df	= new SimpleDateFormat( "MMMM/yyyy", symbols );
		Calendar cal	= Calendar.getInstance();
		
		cal.setTime( this.contratos.getFecha() );
		cal.add(Calendar.YEAR, 1);
		
		PdfPCell cell5 = ComunPDF.createCeldaDescripcion("****************************************************************************************************");
		cell5.setColspan(2);
		cell5.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		t.addCell( ComunPDF.createCeldaDescripcion("NOMBRE DEL CLIENTE:") );
		t.addCell( ComunPDF.createCeldaDescripcion( this.contratos.getTClientes().getNombre() + "  " + this.contratos.getTClientes().getApellidos() ) );
		t.addCell( ComunPDF.createCeldaDescripcion("DIRECCIÓN:") );
		t.addCell( ComunPDF.createCeldaDescripcion( this.contratos.getTClientes().getDireccion() ) );
		t.addCell( ComunPDF.createCeldaDescripcion("FECHA:") );
		t.addCell( ComunPDF.createCeldaDescripcion( df.format( this.contratos.getFecha() ) + " - " + df.format( cal.getTime() ) ) );
		t.addCell( ComunPDF.createCeldaDescripcion("Nº CERTIFICADO:") );
		t.addCell( ComunPDF.createCeldaDescripcion( "" + this.contratos.getNumero() ) );
		t.addCell(cell5);
		
		t.setWidthPercentage(100f);
		
		ct.setBorder(0);
		ct.addElement(t);
		
		table.addCell(ct);
	}
	
	private void createExtintores(PdfPTable table) 
	throws UnsupportedEncodingException
	{
		PdfPTable t	= new PdfPTable(6);
		PdfPCell ct	= new PdfPCell();
		
		Font fnt 	= new Font();
		
		fnt.setSize(10f);
		
		t.setWidthPercentage(100f);
		
		PdfPCell c1	= ComunPDF.createCeldaDescripcion( "DESCRIPCIÓN", fnt );
		PdfPCell c2	= ComunPDF.createCeldaDescripcion( "AGENTE", fnt );
		PdfPCell c3	= ComunPDF.createCeldaDescripcion( "CAPACIDAD \n (KG)", fnt );
		PdfPCell c4	= ComunPDF.createCeldaDescripcion( "FECHA", fnt );
		PdfPCell c5	= ComunPDF.createCeldaDescripcion( "PRUEBA \n REALIZADA", fnt );
		PdfPCell c6	= ComunPDF.createCeldaDescripcion( "CANTIDAD", fnt );
		
		c1.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		c2.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		c3.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		c4.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		c5.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		c6.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		t.addCell(c6);
		t.addCell(c1);
		t.addCell(c2);
		t.addCell(c3);
		t.addCell(c4);
		t.addCell(c5);
		
		for( TLineasContrato l : this.contratos.getTLineasContratos() )
		{
			Integer cantidad 	= l.getCantidad();
			String scantidad	= cantidad == null ? "" : cantidad.toString();
			
			t.addCell( ComunPDF.createCeldaDescripcion( scantidad, fnt ) );
			t.addCell( ComunPDF.createCeldaDescripcion( l.getNumeroPlaca(), fnt ) );
			t.addCell( ComunPDF.createCeldaDescripcion( l.getTAgentes().getDescr(), fnt ) );
			t.addCell( ComunPDF.createCeldaPrecio( l.getCapacidad(), fnt ) );
			t.addCell( ComunPDF.createCeldaFecha( l.getFecha(), fnt ) );
			t.addCell( ComunPDF.createCeldaDescripcion( l.getTPruebas().getDescr(), fnt ) );
		}
		
		PdfPCell asteriscos = ComunPDF.createCeldaDescripcion("****************************************************************************************************");
		PdfPCell leyenda	= ComunPDF.createCeldaDescripcion("RT:RETIMBRADO - RC:RECARGA - RV:REVISIÓN - N:NUEVO");
		
		asteriscos.setColspan(6);
		asteriscos.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		leyenda.setColspan(6);
		leyenda.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		t.addCell(asteriscos);
		t.addCell(leyenda);
				
		ct.setBorder(0);
		ct.addElement(t);
		
		table.addCell(ct);
	}
	
	private void createPie(PdfPTable table) 
	throws BadElementException, MalformedURLException, IOException
	{
		StringBuffer parrafo6 = new StringBuffer();
		parrafo6.append("PRUEBAS REALIZADAS SEGÚN LO MARCA EN TC-MIE-AP/5");
		parrafo6.append("\n");
		parrafo6.append("ASÍ COMO EN EL REGLAMENTO DE APARATOS DE PRESIÓN R.D. 1942/93");
		parrafo6.append("\n");
		parrafo6.append("INGENIERO TÉCNICO COLEGIADO Nº 1032");
		parrafo6.append("\n");
		parrafo6.append("D. JOSÉ MANUEL LÓPEZ CHORRO");
		
		StringBuffer parrafo7 = new StringBuffer();
		parrafo7.append("FIRMA O SELLO");
		parrafo7.append("\n");
		parrafo7.append("EXTINTORES ATIENZA S.L.");		
		
		Phrase phrase1	= new Phrase( parrafo6.toString() );
		
		PdfPCell cell1	= new PdfPCell(phrase1);
		PdfPCell cell2	= new PdfPCell( new Phrase(" ") );
		PdfPCell cell4 	= ComunPDF.createCeldaSello( this.sello );
		
		cell4.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		
		cell1.setBorder(0);
		cell2.setBorder(0);
		cell4.setBorder(0);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell4);
	}
	
	private void createParrafoCliente(PdfPTable table)
	{
		StringBuffer sb1 	= new StringBuffer();

		sb1.append("De una parte como empresa, Extintores J. Atienza S.L., con número de Mantenedor de ");
		sb1.append("Instalaciones contra incendios 29/51, con domicilio en C/ José Palanca, local 12, Bda. ");
		sb1.append("El Torcal, 29003 Málaga, y de otra parte como cliente, ");
		sb1.append( this.contratos.getTClientes().getNombre() + "" + this.contratos.getTClientes().getApellidos() + ", " );
		sb1.append("con domicilio en ");
		sb1.append( this.contratos.getTClientes().getDireccion() + "." );
		sb1.append("\nAmbas partes reconocen tener capacidad legal para contratar y convienen las ");
		sb1.append("siguientes estipulaciones: ");
		
		table.addCell( ComunPDF.createCeldaDescripcion( sb1.toString() ) );
	}
	
	private void createParrafoConforme(PdfPTable table) 
	throws BadElementException, MalformedURLException, IOException
	{
		PdfPTable t	= new PdfPTable(2);		
		PdfPCell tc	= new PdfPCell();
		PdfPCell iz	= ComunPDF.createCeldaDescripcion("Conforme cliente");
		PdfPCell de	= ComunPDF.createCeldaSello( this.sello ) ;
		
		table.setWidthPercentage(100f);

		de.setHorizontalAlignment( PdfPCell.ALIGN_RIGHT );
		
		t.addCell(iz);
		t.addCell(de);
		
		tc.setBorder(0);
		tc.addElement(t);
		
		table.addCell(tc);
	}
	
	private void createParrafoFinal(PdfPTable table)
	{
		PdfPCell c = ComunPDF.createCeldaDescripcion("Póliza de Responsabilidad Civil nº 5-RF-29000434");
		
		c.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );
		table.addCell(c);
	}
	
	private void createPuntosParrafo(PdfPTable table)
	{
		StringBuffer sb3 	= new StringBuffer();
		StringBuffer sb4 	= new StringBuffer();
		StringBuffer sb5 	= new StringBuffer();
		StringBuffer sb6 	= new StringBuffer();
		StringBuffer sb7 	= new StringBuffer();
		StringBuffer sb8 	= new StringBuffer();
		StringBuffer sb9 	= new StringBuffer();

		sb3.append("1.- Mediante las condiciones que más adelante se detallan, Extintores J. Atienza S.L., ");
		sb3.append("se compromete a hacer el mantenimiento anual de las instalaciones que a continuación ");
		sb3.append("se detallan, según N.B.E.-CPI-96 y R.D. 1942/1993 del 5 de noviembre por el que se ");
		sb3.append("aprueba el reglamento de instalaciones de protección contra incendios. ");
		sb3.append("\n ");
		sb3.append("Las normas UNE que se citan en el R.D., son de obligado cumplimiento desde el 14 de ");
		sb3.append("marzo de 1994 ");

		sb4.append("2.- Extintores J.Atienza S.L. garantiza el mantenimiento de todas las instalaciones ");
		sb4.append("antes detalladas durante un año, realizando una inspección técnica con carácter de un ");
		sb4.append("año a partir de la fecha de firma del presente contrato y atendiendo, sin cargo, en caso ");
		sb4.append("de avería durante un año ");

		sb5.append("3.- Las operaciones de mantenimiento de instalación y recarga de extintores por ");
		sb5.append("utilización o manipulación inadecuada y la reposición de elementos averiados, serán ");
		sb5.append("por cuenta del cliente, ya que este contrato es sin garantía de equipo, efectuándose ");
		sb5.append("una bonificación del 15% sobre los trabajos realizados que no estén incluidos en el ");
		sb5.append("presente contrato. ");
		sb5.append("\n ");
		sb5.append("Por su parte, Extintores J.Atienza S.L., entregará anualmente el oportuno Certificado ");
		sb5.append("de Revisión del material contratato a efectos del Sindicato Nacional y Compañía ");
		sb5.append("Aseguradora. Para el presente contrato, el Certificado de Revisión es el nº " + this.contratos.getNumero() );

		sb6.append("4.- Este contrato empezará a regir el día " + this.calculaFecha() + " y su duración será de un año, ");
		sb6.append("considerándose después tácticamene prorrogado por igual periodo sucesivo mientras ");
		sb6.append("una de las partes no lo denuncie con sesenta (60) días de antelación al vencimiento ");

		sb7.append("5.- Será causa de rescisión del contrato, el mal uso de los materiales o la mala ");
		sb7.append("manipulación de los mismos, por el personal ajeno a la empresa Extintores J.Atienza ");
		sb7.append("S.L. ");

		sb8.append("6.- Extintores J.Atienza S.L. y el cliente para cualquier duda que pudiera surgir del ");
		sb8.append("presente contrato, renunciana a su fuero especial si lo tuvieran y se someten ");
		sb8.append("expresamente a los jueces y tribunales de Málaga ");

		sb9.append("7.- El precio del presente contrato en las condiciones estipuladas es de " + this.calculaCoste() + "€ más ");
		sb9.append("IVA y podrá ser revisado anualmente según el I.P.C. ");
		
		table.addCell( ComunPDF.createCeldaDescripcion( sb3.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb4.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb5.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb6.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb7.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb8.toString() ) );
		table.addCell( ComunPDF.createCeldaDescripcion("") );
		table.addCell( ComunPDF.createCeldaDescripcion( sb9.toString() ) );
	}
	
	private void createParrafoAnexo(PdfPTable table) 
	throws BadElementException, MalformedURLException, IOException
	{
		PdfPCell sello = ComunPDF.createCeldaSello( this.sello );
		
		sello.setHorizontalAlignment( PdfPCell.ALIGN_CENTER );		
		
		table.addCell( ComunPDF.createCeldaTH("ANEXO") );
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		table.addCell( ComunPDF.createCeldaDescripcion( this.contratos.getAnexo() ) );
		table.addCell( ComunPDF.createCeldaDescripcion(" ") );
		table.addCell(sello);
	}
	
	private String calculaCoste()
	{
		float coste 	= 0f;
		NumberFormat nf = new DecimalFormat("###,###,##0.00");
		
		for( TLineasContrato lc : this.contratos.getTLineasContratos() )
		{
			coste += lc.getPrecio();	
		}
		
		return nf.format(coste);
	}
	
	private String calculaFecha()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format( this.contratos.getFecha() );
	}
}