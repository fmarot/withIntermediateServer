import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApp {

	public static void main(String[] args) throws Exception {
		ServerApp appServer = new ServerApp();
		appServer.start();
	}

	private void start() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppSpringConfig.class);
		Object obj = context.getBean("serverFactory");

		while (true) {
			synchronized (this) {
				this.wait();
			}
		}
	}
}