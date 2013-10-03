import java.util.List;

public interface PresetService {

	PresetDTO getPresetByName(String name);

	List<PresetDTO> getAllPresets();

}
