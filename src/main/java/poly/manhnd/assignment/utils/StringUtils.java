package poly.manhnd.assignment.utils;

import java.io.IOException;

public class StringUtils {

	public static boolean isNumberic(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static String renameFile(String filename) throws IOException {
		if(filename==null|| filename.trim().isEmpty()) {
			throw new IOException("File is empty!");
		}
		String name = filename.substring(0, filename.lastIndexOf("."));
		name = name
				.replaceAll("\\\\", "-")
				.replaceAll("/", "-")
				.replaceAll("\\.+", "")
				.replaceAll("\\,+", "")
				.replaceAll("\\s+", " ")
				.trim()
				.replaceAll("\\s", "-");
		String ext = filename.substring(filename.lastIndexOf(".")).replaceAll("\\s+", "");
		filename = VNCharacterUtil.removeAccent(name+ext);
		return filename;
	}
	
	public static String getFileExtension(String filename) throws IOException {
		if(filename==null|| filename.trim().isEmpty()) {
			throw new IOException("File is empty!");
		}
		filename = filename.substring(filename.lastIndexOf(".")).replaceAll("\\s+", "");
		return filename;
	}
	
}
