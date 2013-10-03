import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApp {

	private static final Logger	log	= LoggerFactory.getLogger(ClientApp.class);

	private void start() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
		Map<String, IImageService> beansOfType = context.getBeansOfType(IImageService.class);
		for (IImageService service : beansOfType.values()) {
			ImageServiceMetaData serviceMetaData = service.getServiceMetaData();
			log.warn("Service metaData: {}", serviceMetaData);
		}
	}

	public static void main(String[] args) throws Exception {
		ClientApp clientApp = new ClientApp();
		clientApp.start();
	}

}