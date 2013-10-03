import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/** Spring's config but in Java instead of XML :) */
@Configuration
public class AppSpringConfig {

	@Bean
	public PresetService imageServiceLocal() {
		return new IImageService() {

			@Override
			public ImageServiceMetaData getServiceMetaData() {
				ImageServiceMetaData serviceMetadata = new ImageServiceMetaData();
				serviceMetadata.setServiceVersion("running locally !!!");
				return serviceMetadata;
			}
		};
	}

	/** La FactoryBean permet en fait d'obtenir un IImageService de facon transparente */
	@Bean
	public HttpInvokerProxyFactoryBean imageServiceRemote() {
		HttpInvokerProxyFactoryBean service = new HttpInvokerProxyFactoryBean();
		service.setServiceInterface(IImageService.class);
		String url = "http://127.0.0.1:" + IImageService.SERVICE_PORT + IImageService.DEFAULT_CONTEXT_ROOT;
		service.setServiceUrl(url);
		return service;
	}

}