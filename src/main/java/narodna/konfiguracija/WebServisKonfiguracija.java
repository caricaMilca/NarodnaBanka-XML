package narodna.konfiguracija;

import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@EnableWs
@Configuration
public class WebServisKonfiguracija extends WsConfigurerAdapter {

/*	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	/*@Bean(name = "nalogBean")
	public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("nalog") XsdSchema nalog){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("nalogPort");
		wsdl11Definition.setLocationUri("/ws");
	//	wsdl11Definition.setTargetNamespace("http://Firma.com/ws/");
	//	wsdl11Definition.setSchemaCollection(schemaCollection);
		wsdl11Definition.setSchema(nalog);
		return wsdl11Definition;
	}*/
	/*
	@Bean(name = "banka")
	public DefaultWsdl11Definition defaultWsdl11Definition(CommonsXsdSchemaCollection schemaCollection)
			throws Exception {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("bankaPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://banka/ws/");
		wsdl11Definition.setSchemaCollection(schemaCollection);
		return wsdl11Definition;
	}

	
	@Bean
	public CommonsXsdSchemaCollection schemeCollection() {
		CommonsXsdSchemaCollection collection = new CommonsXsdSchemaCollection(
				new Resource[] {new ClassPathResource("/nalog.xsd")});
		collection.setInline(true);
		return collection;
	}

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("banka.nalog");
		return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {

		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri("http://localhost:8080/ws");

		return webServiceTemplate;
	}

	@Bean
	@Qualifier("nalog")
	public XsdSchema nalog() {
		return new SimpleXsdSchema(new ClassPathResource("nalog.xsd"));
	}*/
}
