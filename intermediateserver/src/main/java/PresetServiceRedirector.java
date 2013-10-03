import lombok.Delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



public class PresetServiceRedirector implements PresetService {

	@Autowired
	@Qualifier("remotePresetService")
	@Delegate
	private PresetService	presetService;

}
