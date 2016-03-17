package es.damoresa.jasperutils.builder;

import es.damoresa.jasperutils.modelo.JasperConfiguration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * <p>Interfaz del servicio generador de <code>JasperPrint</code>s.</p>
 * <p>Se utiliza para simplificar la configuración y mantenimiento de 
 * la generación de informes <code>JasperReport</code>.</p>
 * 
 * @author damores
 * @since 16/02/2016
 */
public interface JasperBuilder {
	
	/**
	 * <p>Método que genera el <code>JasperPrint</code> de la solicitud 
	 * indicada, perfilándola en función del usuario activo.</p>
	 * 
	 * @param configuracionInforme <code>JasperConfiguration</code> con la 
	 * configuración del informe JasperReports a generar.
	 * @return <code>JasperPrint</code> con el informe <code>JasperReport</code> 
	 * relleno.
	 * @throws <code>JRException</code> en caso de que la compilación de alguna de las 
	 * plantillas de <i>JasperReports</i> definidas en la configuración falle.
	 */
	public JasperPrint generarPdf(JasperConfiguration configuracionInforme) throws JRException;
}
