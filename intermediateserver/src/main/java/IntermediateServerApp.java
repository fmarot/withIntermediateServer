import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IntermediateServerApp {

	public static void main(String[] args) throws Exception {
		IntermediateServerApp appServer = new IntermediateServerApp();
		appServer.start();
	}

	private void start() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
		Object exposedService = context.getBean(PresetServiceRedirector.class);

		while (true) {
			synchronized (this) {
				this.wait();
			}
		}
	}
}