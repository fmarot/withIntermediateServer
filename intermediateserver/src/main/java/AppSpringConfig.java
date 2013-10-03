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

	/* ************* Remote service accessed ************** */
	@Bean(name = "remotePresetService")
	public HttpInvokerProxyFactoryBean remotePresetService() {
		HttpInvokerProxyFactoryBean service = new HttpInvokerProxyFactoryBean();
		service.setServiceInterface(PresetService.class);
		String url = "http://127.0.0.1:" + PresetService.PORT_SERVER + PresetService.CONTEXT_ROOT;
		service.setServiceUrl(url);
		return service;
	}

	/* *********** Local service exposed in HTTPInvoker ************ */
	@Bean
	public PresetService localPresetService() {
		return new PresetServiceImpl();
	}

	@Bean
	public PresetService exposedPresetService() {
		return new PresetServiceRedirector();
	}

	@Bean
	public SimpleHttpInvokerServiceExporter serviceExporter() {
		SimpleHttpInvokerServiceExporter exporter = new SimpleHttpInvokerServiceExporter();
		exporter.setService(this.exposedPresetService());
		exporter.setServiceInterface(PresetService.class);
		return exporter;
	}

	@Bean
	public SimpleHttpServerFactoryBean presetService() {
		Map<String, HttpHandler> contexts = new HashMap<String, HttpHandler>();
		contexts.put(PresetService.CONTEXT_ROOT, serviceExporter());
		SimpleHttpServerFactoryBean serverFactory = new SimpleHttpServerFactoryBean();
		serverFactory.setContexts(contexts);
		serverFactory.setPort(PresetService.PORT_INTERMEDIATE_SERVER);
		return serverFactory;
	}

}