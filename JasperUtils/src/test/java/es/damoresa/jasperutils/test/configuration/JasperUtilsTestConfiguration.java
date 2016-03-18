package es.damoresa.jasperutils.test.configuration;

import es.damoresa.jasperutils.configuration.JasperUtilsConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>Clase <code>Configuration</code> que contiene la configuración
 * de tests de Spring para permitir inyectar el servicio de construcción de
 * <i>JasperReports</i>.</p>
 *
 * @author damores
 * @since 18/03/2016
 */
@Configuration
@ComponentScan(basePackages = "es.damoresa.jasperutils.test")
@Import({JasperUtilsConfiguration.class})
public class JasperUtilsTestConfiguration {
}
