import java.util.List;

public interface PresetService {

	public final static int PORT_SERVER = 8090;
	public final static int PORT_INTERMEDIATE_SERVER = 8091;
	public final static String CONTEXT_ROOT = "/remoting/PresetService";

	PresetDTO getPresetByName(String name);

	List<PresetDTO> getAllPresets();

}
