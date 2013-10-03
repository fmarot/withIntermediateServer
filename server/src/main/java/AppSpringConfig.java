import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;

import com.sun.net.httpserver.HttpHandler;

/** Spring's config but in Java instead of XML :) */
@Configuration
public class AppSpringConfig {

	@Bean
	public ImageService imageService() {
		return new ImageService();
	}

	@Bean
	public SimpleHttpInvokerServiceExporter serviceExporter() {
		SimpleHttpInvokerServiceExporter exporter = new SimpleHttpInvokerServiceExporter();
		exporter.setService(this.imageService());
		exporter.setServiceInterface(IImageService.class);
		return exporter;
	}

	@Bean
	public SimpleHttpServerFactoryBean serverFactory() {
		Map<String, HttpHandler> contexts = new HashMap<String, HttpHandler>();
		contexts.put(IImageService.DEFAULT_CONTEXT_ROOT, serviceExporter());
		SimpleHttpServerFactoryBean serverFactory = new SimpleHttpServerFactoryBean();
		serverFactory.setContexts(contexts);
		serverFactory.setPort(IImageService.SERVICE_PORT);
		return serverFactory;
	}
}