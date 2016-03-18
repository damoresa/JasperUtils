package es.damoresa.jasperutils.test.factory.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;

import es.damoresa.jasperutils.modelo.JasperConfiguration;
import es.damoresa.jasperutils.modelo.JasperDatasourceConfiguration;
import es.damoresa.jasperutils.test.factory.JasperConfigurationFactory;
import es.damoresa.jasperutils.test.model.TestModelClass;

/**
 * <p>Implementación de ejemplo de <code>JasperConfigurationFactory</code>.</p>
 * 
 * @author damores
 * @since 18/03/2016
 */
@Service("JasperConfigurationFactoryImpl")
public class JasperConfigurationFactoryImpl implements JasperConfigurationFactory {
	
	private static final TestModelClass datosEjemplo = new TestModelClass("Prueba", "prueba", "Esto es una prueba");
	private static final List<TestModelClass> lstDatosEjemplos = new ArrayList<TestModelClass>();
	
	static
	{
		lstDatosEjemplos.add(new TestModelClass("Prueba", "prueba", "Esto es una prueba"));
		lstDatosEjemplos.add(new TestModelClass("Prueba 2", "prueba2", "Esto es otra prueba"));
		lstDatosEjemplos.add(new TestModelClass("Prueba 3", "prueba3", "Esto la prueba final"));
	}

    @Override
    public JasperConfiguration generateJasperConfiguration() {
        return this.reportEjemplo();
    }
    
    /**
     * <p>Método que genera una configuración de subreport de ejemplo.</p>
     * 
     * @return <code>JasperConfiguration</code> con datos de un subinforme de prueba.
     */
	private JasperConfiguration subreportEjemplo() {
		
		JasperDatasourceConfiguration datasource = new JasperDatasourceConfiguration();
		datasource.setDatasourceName("ejemploSubreportDatasource");
		datasource.setDatasource(new JRBeanCollectionDataSource(lstDatosEjemplos));

		JasperConfiguration ejemploSubreport = new JasperConfiguration();
		ejemploSubreport.setReportName("ejemploSubreport");
		ejemploSubreport.setTemplatePath("/jasper/EjemploSubreport.jrxml");
		ejemploSubreport.setDatasourceConfig(datasource);
		
		return ejemploSubreport;
	}
    
    /**
     * <p>Método que genera una configuración de ejemplo.</p>
     * 
     * @return <code>JasperConfiguration</code> con datos de un informe de prueba.
     */
	private JasperConfiguration reportEjemplo() {
		
		JasperDatasourceConfiguration datasource = new JasperDatasourceConfiguration();
		datasource.setDatasourceName("ejemploDatasource");
		datasource.setDatasource(new JREmptyDataSource());

		JasperConfiguration ejemploReport = new JasperConfiguration();
		ejemploReport.setReportName("ejemploReport");
		ejemploReport.setTemplatePath("/jasper/Ejemplo.jrxml");
		ejemploReport.setDatasourceConfig(datasource);
		
		ejemploReport.addParameter("datosEjemplo", datosEjemplo);
		
		ejemploReport.addSubreport(this.subreportEjemplo());
		
		return ejemploReport;
	}
}
