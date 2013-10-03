import java.util.ArrayList;
import java.util.List;

public class PresetServiceImpl implements PresetService {

	@Override
	public PresetDTO getPresetByName(String name) {
		return new PresetDTO(name);
	}

	@SuppressWarnings(value = { "rawtypes", "unchecked", "serial" })
	@Override
	public List<PresetDTO> getAllPresets() {
		// inner classes are not deserializable by the client
		return new ArrayList(new ArrayList() {
			{
				add(new PresetDTO("preset1"));
				add(new PresetDTO("preset2"));
				add(new PresetDTO("preset3"));
			}
		});
	}
}
