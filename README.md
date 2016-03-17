# JasperUtils
Librería de útiles para la generación de JasperReports.

# Configuración
Mediante Spring 4, simplemente es necesario importar la configuración JavaDoc de la clase 
JasperUtilsConfiguration y tendremos acceso al Business Object de generación de JasperPrints 
mediante los objetos de modelo JasperConfiguration.

Para disponer de este servicio, basta con declarar la interfaz y anotarla con @Autowired.

Ejemplo:

@Autowired

private JasperBuilder jasperBuilder;
