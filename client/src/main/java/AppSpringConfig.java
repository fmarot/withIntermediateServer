import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class AppSpringConfig {

	/** La FactoryBean permet en fait d'obtenir un PresetService de facon transparente */
	@Bean
	public HttpInvokerProxyFactoryBean presetServiceRemote() {
		HttpInvokerProxyFactoryBean service = new HttpInvokerProxyFactoryBean();
		service.setServiceInterface(PresetService.class);
		String url = "http://127.0.0.1:8080/remoting/PresetService";
		service.setServiceUrl(url);
		return service;
	}

}