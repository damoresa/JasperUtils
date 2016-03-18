package es.damoresa.jasperutils.test.factory;

import es.damoresa.jasperutils.modelo.JasperConfiguration;

/**
 * <p><code>Interfaz</code> del <i>Factory</i> de objetos 
 * <code>JasperConfiguration</code> para simplificar la creación 
 * de configuraciones.</p>
 * 
 * @author damores
 * @since 18/03/2016
 */
public interface JasperConfigurationFactory {
	
	/**
	 * <p>Método que genera un objeto <code>JasperConfiguration</code>.</p>
	 * <p><b>NOTA</b>: Se podría informar un <code>Enum</code> como 
	 * parámetro que permitiera generar distintos PDFs en función de la 
	 * configuración.<br>
	 * Además, este <i>factory</i> debe tener inyectados los servicios necesarios 
	 * para recuperar los datos necesarios en la generación de los 
	 * <code>JasperConfiguration</code>.</p>
	 * 
	 * @return <code>JasperConfiguration</code> con los datos del informe 
	 * <i>JasperReports</i> a generar.
	 */
    public JasperConfiguration generateJasperConfiguration();
}
