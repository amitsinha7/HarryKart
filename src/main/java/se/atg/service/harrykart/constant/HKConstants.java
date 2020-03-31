package se.atg.service.harrykart.constant;

import java.util.HashMap;
import java.util.Map;

public class HKConstants {

	public static final Map<String, String> errorStore = new HashMap<String, String>();

	static {
		errorStore.put("110001", "Something went wrong, please try again later.");
		errorStore.put("110002", "Bad request, please check request.");
		errorStore.put("110003", "IOException Occured.. While Deserialize the input XML To Oject Mapper..");
	}
}
