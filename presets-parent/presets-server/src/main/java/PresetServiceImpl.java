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
		// inner classes are not deserializable by the client so we wrap into another 'real' ArrayList
		return new ArrayList(new ArrayList() {
			{
				add(new PresetDTO("preset1"));
				add(new PresetDTO("preset2"));
				add(new PresetDTO("preset3"));
			}
		});
		/*
		ArrayList<PresetDTO> allPresets = new ArrayList<PresetDTO>();
		allPresets.add(new PresetDTO("preset1"));
		allPresets.add(new PresetDTO("preset2"));
		allPresets.add(new PresetDTO("preset3"));
		return allPresets;
		 */
	}
}
