package es.damoresa.jasperutils.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Clase empleada para modelar la configuración de un informe <code>JasperReport</code>.<br>
 * Permite, entre otros, la configuración de nombre del report, recursos gráficos (imágenes), 
 * datasources, subreports, localización de la plantilla JRXML y parámetros.</p> 
 * 
 * @author damores
 * @since 16/02/2016
 */
public class JasperConfiguration implements java.io.Serializable {
	
	private static final long serialVersionUID = 1987811091166645213L;
	
	private String reportName;
	private List<JasperResource> imageResources;
	private JasperDatasourceConfiguration datasourceConfig;
	private String templatePath;
	private List<JasperConfiguration> subreports;
	private Map<String, Object> parameters;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public List<JasperResource> getImageResources() {
		return imageResources;
	}

	public void setImageResources(List<JasperResource> imageResources) {
		this.imageResources = imageResources;
	}
	
	public void addImageResource(JasperResource imageResource) {
		
		if (this.imageResources == null)
		{
			this.imageResources = new ArrayList<JasperResource>();
		}
		
		this.imageResources.add(imageResource);
	}
	
	public void addAllImageResources(List<JasperResource> imageResources) {
		
		if (this.imageResources == null)
		{
			this.imageResources = new ArrayList<JasperResource>();
		}
		
		this.imageResources.addAll(imageResources);
	}

	public JasperDatasourceConfiguration getDatasourceConfig() {
		return datasourceConfig;
	}

	public void setDatasourceConfig(JasperDatasourceConfiguration datasourceConfig) {
		this.datasourceConfig = datasourceConfig;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public List<JasperConfiguration> getSubreports() {
		return subreports;
	}

	public void setSubreports(List<JasperConfiguration> subReports) {
		this.subreports = subReports;
	}
	
	public void addSubreport(JasperConfiguration jasperConfiguration) {
		
		if (this.subreports == null)
		{
			this.subreports = new ArrayList<JasperConfiguration>();
		}
		
		this.subreports.add(jasperConfiguration);
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(String key, Object parameter) {
		
		if (this.parameters == null)
		{
			this.parameters = new HashMap<String, Object>();
		}
		
		this.parameters.put(key, parameter);
	}
	
	public void addAllParameters(Map<String, Object> parameters) {
		
		if (this.parameters == null)
		{
			this.parameters = new HashMap<String, Object>();
		}
		
		this.parameters.putAll(parameters);
	}
}
