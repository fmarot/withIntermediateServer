import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ClientApp {

	private void start() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
		PresetService presetService = context.getBean(PresetService.class);
		List<PresetDTO> allPresets = presetService.getAllPresets();
		log.info("allPresets: {}", allPresets);
	}

	public static void main(String[] args) throws Exception {
		ClientApp clientApp = new ClientApp();
		clientApp.start();
	}

}