package es.damoresa.jasperutils.test.example;

import java.io.File;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.damoresa.jasperutils.builder.JasperBuilder;
import es.damoresa.jasperutils.modelo.JasperConfiguration;
import es.damoresa.jasperutils.test.configuration.JasperUtilsTestConfiguration;
import es.damoresa.jasperutils.test.factory.JasperConfigurationFactory;

/**
 * <p><i>Test class</i> para generar un PDF de prueba.</p>
 * 
 * @author damores
 * @since 18/03/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JasperUtilsTestConfiguration.class)
public class JasperUtilsTest {

    @Autowired
    @Qualifier("JasperConfigurationFactoryImpl")
    private JasperConfigurationFactory jasperConfigurationFactory;

    @Autowired
    @Qualifier("JasperBuilderImpl")
    private JasperBuilder jasperBuilder;

    @Test
    public void testGeneracionReport()
    {
        JasperConfiguration jasperConfiguration = jasperConfigurationFactory.generateJasperConfiguration();
        
        Assert.assertTrue(jasperConfiguration != null);
        
        try
        {
        	JasperPrint print = jasperBuilder.generarPdf(jasperConfiguration);
            
            Assert.assertTrue(print != null);
            
            File directorio = new File("pruebas");
            if (!directorio.exists())
            {
            	directorio.mkdirs();
            }
            
            Assert.assertTrue(directorio.exists());
			
			JasperExportManager.exportReportToPdfFile(print, "pruebas/ejemplo.pdf");
        }
        catch (JRException jrException)
        {
        	Assert.fail(jrException.getMessage());
        }
    }
}
