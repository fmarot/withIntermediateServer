import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class AppSpringConfig {

	int PORT = PresetService.PORT_INTERMEDIATE_SERVER;

	// int PORT = PresetService.PORT_SERVER;

	/**
	 * La FactoryBean permet en fait d'obtenir un PresetService de facon
	 * transparente
	 */
	@Bean
	public HttpInvokerProxyFactoryBean presetServiceRemote() {
		HttpInvokerProxyFactoryBean service = new HttpInvokerProxyFactoryBean();
		service.setServiceInterface(PresetService.class);
		String url = "http://127.0.0.1:" + PORT + PresetService.CONTEXT_ROOT;
		service.setServiceUrl(url);
		return service;
	}

}