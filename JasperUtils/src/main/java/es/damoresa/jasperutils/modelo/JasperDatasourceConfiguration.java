package es.damoresa.jasperutils.modelo;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * <p>Clase empleada para modelar la configuración de un informe <code>JasperReport</code>.<br>
 * Permite definir un nombre para el datasource y un <code>JRDataSource</code> con su contenido.</p>
 * 
 * @author damores
 * @since 16/02/2016
 */
public class JasperDatasourceConfiguration implements java.io.Serializable {
	
	private static final long serialVersionUID = 4608429648240145720L;
	
	private String datasourceName;
	private JRDataSource datasource;

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public JRDataSource getDatasource() {
		return datasource;
	}

	public void setDatasource(JRDataSource datasource) {
		this.datasource = datasource;
	}
}
