import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;

import com.sun.net.httpserver.HttpHandler;

/** Spring's config but in Java instead of XML :) */
@Configuration
public class AppSpringConfig {

	/* *********** Local service exposed in HTTPInvoker ************ */
	@Bean
	public PresetService localPresetService() {
		return new PresetServiceImpl();
	}

	@Bean
	public SimpleHttpInvokerServiceExporter serviceExporter() {
		SimpleHttpInvokerServiceExporter exporter = new SimpleHttpInvokerServiceExporter();
		exporter.setService(this.localPresetService());
		exporter.setServiceInterface(PresetService.class);
		return exporter;
	}

	@Bean
	public SimpleHttpServerFactoryBean presetService() {
		Map<String, HttpHandler> contexts = new HashMap<String, HttpHandler>();
		contexts.put("/remoting/PresetService", serviceExporter());
		SimpleHttpServerFactoryBean serverFactory = new SimpleHttpServerFactoryBean();
		serverFactory.setContexts(contexts);
		serverFactory.setPort(8080);
		return serverFactory;
	}

	/* ************* Remote service accessed ************** */
	@Bean
	public HttpInvokerProxyFactoryBean remotePresetService() {
		HttpInvokerProxyFactoryBean service = new HttpInvokerProxyFactoryBean();
		service.setServiceInterface(PresetService.class);
		String url = "http://127.0.0.1:8090/remoting/PresetService";
		service.setServiceUrl(url);
		return service;
	}
}