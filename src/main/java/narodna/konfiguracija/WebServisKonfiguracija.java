package narodna.konfiguracija;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServisKonfiguracija extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "narodna")
	public DefaultWsdl11Definition defaultWsdl11Definition(CommonsXsdSchemaCollection schemaCollection)
			throws Exception {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("narodnaPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://narodna/ws/");
		wsdl11Definition.setSchemaCollection(schemaCollection);
		return wsdl11Definition;
	}

	
	@Bean
	public CommonsXsdSchemaCollection schemeCollection() {
		CommonsXsdSchemaCollection collection = new CommonsXsdSchemaCollection(
				new Resource[] {new ClassPathResource("/mt103.xsd"),new ClassPathResource("/mt900.xsd"),
						new ClassPathResource("/mt910.xsd"), new ClassPathResource("/mt102.xsd")});
		collection.setInline(true);
		return collection;
	}

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPaths("narodna.mt103", "narodna.mt900", "narodna.mt910", "narodna.mt102");
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

}
