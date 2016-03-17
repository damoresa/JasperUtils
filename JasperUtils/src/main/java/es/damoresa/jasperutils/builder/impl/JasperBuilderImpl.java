package es.damoresa.jasperutils.builder.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import es.damoresa.jasperutils.builder.JasperBuilder;
import es.damoresa.jasperutils.modelo.JasperConfiguration;
import es.damoresa.jasperutils.modelo.JasperDatasourceConfiguration;
import es.damoresa.jasperutils.modelo.JasperResource;

@Component("JasperBuilderImpl")
public class JasperBuilderImpl implements JasperBuilder {
	
	private static final Logger logger = Logger.getLogger(JasperBuilderImpl.class);
	
	@Override
	public JasperPrint generarPdf(JasperConfiguration configuracionInforme)
		throws JRException
	{
		JasperPrint print = this.generatePrint(configuracionInforme);
		
		return print;
	}
	
	/**
	 * <p>Método que genera un objeto <code>JasperPrint</code> a partir del objeto 
	 * <code>JasperConfiguration</code> de configuración del informe a generar.</p>
	 * 
	 * @author damores
	 * @param jasperConfiguracion <code>JasperConfiguration</code> con los datos de 
	 * configuración del informe Jasper a generar.
	 * @return <code>JasperPrint</code> con el informe relleno a partir de la configuración 
	 * indicada.
	 * @throws <code>JRException</code> en caso de que la compilación de alguna de las 
	 * plantillas de <i>JasperReports</i> definidas en la configuración falle.
	 */
	private JasperPrint generatePrint(JasperConfiguration jasperConfiguracion) 
			throws JRException
	{
		List<InputStream> lstInputStream = new ArrayList<InputStream>();
		
		try
		{
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Generando informe: " + jasperConfiguracion.getReportName());
			}
			
			// 1. Generamos el report y todos sus descendientes
			this.generateReport(jasperConfiguracion, lstInputStream);
			
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Rellenando report ");
			}
			
			// 2. Recuperamos el JasperReport principal y los rellenamos con los datos generados
			JasperReport jasperReport = (JasperReport) jasperConfiguracion.getParameters().get(jasperConfiguracion.getReportName());
			JasperPrint print = JasperFillManager.fillReport(jasperReport, jasperConfiguracion.getParameters(), jasperConfiguracion.getDatasourceConfig().getDatasource());
			
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Informe " + jasperConfiguracion.getReportName() + " generado ");
			}
			
			return print;
		}
		catch (JRException jrEx)
		{
			logger.error(" ## Error generando el PDF base", jrEx);
			throw jrEx;
		}
		finally
		{
			if (!lstInputStream.isEmpty())
			{
				for (InputStream inStream : lstInputStream)
				{
					if (inStream != null)
					{
						try
						{
							inStream.close();
						}
						catch (IOException ioEx) {}
					}
				}
			}
		}
	}
	
	/**
	 * <p>Método que genera los datos de los <i>report</i>, empezando por el report principal.<br>
	 * Posteriormente, se van procesando cada uno de los <i>subreport</i> y se van añadiendo 
	 * las compilaciones, datasources y recursos al conjunto de parámetros del <i>report</i> de 
	 * nivel inmediatamente superior para, finalmente, incluir todos los parámetros necesarios 
	 * del <i>report</i> a la hora de su relleno.</p>
	 * 
	 * @author damores
	 * @param jasperConfiguracion <code>JasperConfiguration</code> con los datos de 
	 * configuración del subreport Jasper a generar.
	 * @param lstInputStream <code>List</code> de <code>InputStream</code> donde se almacenan 
	 * todos los <code>InputStream</code> abiertos para permitir cerrarlos en caso de excepción 
	 * o al terminar la ejecución.
	 * @throws <code>JRException</code> en caso de que la compilación de alguna de las 
	 * plantillas de <i>JasperReports</i> definidas en la configuración falle.
	 */
	private void generateReport(JasperConfiguration jasperConfiguracion, List<InputStream> lstInputStream)
		throws JRException
	{
		try
		{
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Procesando el report: " + jasperConfiguracion.getReportName());
				logger.debug(" >> Añadiendo recursos ");
			}
			
			// 1. Añadimos los recursos gráficos definidos
			if (jasperConfiguracion.getImageResources() != null)
			{
				for (JasperResource imageResource : jasperConfiguracion.getImageResources())
				{
					InputStream image = getClass().getResourceAsStream(imageResource.getResourcePath());
					jasperConfiguracion.addParameter(imageResource.getResourceName(), image);
					lstInputStream.add(image);
				}
			}
			
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Generando subreports ");
			}
			
			// 2. Generamos y añadimos a los parámetros los subreports
			if (jasperConfiguracion.getSubreports() != null)
			{
				for (JasperConfiguration subreportConfig : jasperConfiguracion.getSubreports())
				{
					// 2.1 Generamos los datos del subreport
					this.generateReport(subreportConfig, lstInputStream);
					
					// 2.2 Añadimos los datos a nuestra configuración para permitir 
					//		utilizar los recursos de niveles inferiores al generar el
					//		informe.
					if (subreportConfig.getImageResources() != null)
					{
						jasperConfiguracion.addAllImageResources(subreportConfig.getImageResources());
					}
					
					if (subreportConfig.getParameters() != null)
					{
						jasperConfiguracion.addAllParameters(subreportConfig.getParameters());
					}
				}
			}
			
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Compilando report " + jasperConfiguracion.getReportName());
			}
			
			// 3. Generamos el subreport
			InputStream jrxml = getClass().getResourceAsStream(jasperConfiguracion.getTemplatePath());
			lstInputStream.add(jrxml);
			
			JasperDesign jasperDesign = JRXmlLoader.load(jrxml);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			// 4. Añadimos el subreport a los parámetros
			jasperConfiguracion.addParameter(jasperConfiguracion.getReportName(), jasperReport);
			
			// 5. Añadimos el datasource, si existe, a los parámetros
			if (jasperConfiguracion.getDatasourceConfig() != null)
			{
				JasperDatasourceConfiguration datasourceConfig = jasperConfiguracion.getDatasourceConfig();
				jasperConfiguracion.addParameter(datasourceConfig.getDatasourceName(), datasourceConfig.getDatasource());
			}
			
			if (logger.isDebugEnabled())
			{
				logger.debug(" >> Report " + jasperConfiguracion.getReportName() + " compilado ");
			}
		}
		catch (JRException jrEx)
		{
			logger.error(" ## Error generando el PDF " + jasperConfiguracion.getReportName(), jrEx);
			throw jrEx;
		}
	}
}
