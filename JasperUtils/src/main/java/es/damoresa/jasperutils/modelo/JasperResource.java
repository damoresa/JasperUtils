package es.damoresa.jasperutils.modelo;

/**
 * <p>Clase empleada para modelar la configuración de un recurso para un <code>JasperReport</code>.<br>
 * Permite definir un nombre y la ruta en la que se encuentra el recurso.<br>
 * <b>NOTA</b>: El recurso debe existir en el classpath.</p>
 * 
 * @author damores
 * @since 16/02/2016
 */
public class JasperResource implements java.io.Serializable {
	
	private static final long serialVersionUID = 1234002617159997012L;
	
	private String resourceName;
	private String resourcePath;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
}
