package es.damoresa.jasperutils.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Clase <code>Configuration</code> que contiene la configuración 
 * de Spring para permitir inyectar el servicio de construcción de 
 * <i>JasperReports</i>.</p>
 * 
 * @author damores
 * @since 17/03/2016
 */
@Configuration
@ComponentScan(basePackages = "es.damoresa.jasperutils")
public class JasperUtilsConfiguration {

}
