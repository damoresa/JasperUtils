package es.damoresa.jasperutils.test.model;

/**
 * <p>Clase <code>ModelClass</code> para modelar datos del test.</p>
 *
 * @author damores
 * @since 18/03/2016
 */
public class TestModelClass implements java.io.Serializable {
	
	private static final long serialVersionUID = -6701796491965474396L;
	
	private String nombre;
    private String login;
    private String descripcion;
    
    public TestModelClass() {}

    public TestModelClass(String nombre, String login, String descripcion) {
		super();
		this.nombre = nombre;
		this.login = login;
		this.descripcion = descripcion;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
