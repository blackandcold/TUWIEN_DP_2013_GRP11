package recorder;

import recorder.windows.WindowsRecorder;

/**
 * Can be used to create new recorders
 * @author Stefan Weghofer
 */
public class RecorderFactory {

	/**
	 * Creates a new recorder for the given type
	 * @param type of the recorder
	 * @return a new instance of the IRecorder interface which can serve the specified type
	 */
	public static IRecorder createRecorder(RecorderType type){
		switch(type){
			case Windows:
				return new WindowsRecorder();
			default:
				throw new UnsupportedOperationException();
		}
	}
	
}
