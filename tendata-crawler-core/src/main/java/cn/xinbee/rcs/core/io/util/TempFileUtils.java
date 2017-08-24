package cn.xinbee.rcs.core.io.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;

public abstract class TempFileUtils {

    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    
	public static File generate(String rootDir, String filename) {
		return new File(rootDir, filename);
	}

	public static File generate(String rootDir, String[] dirs, String filename) {
		File directoryFile = new File(rootDir, StringUtils.join(dirs, File.pathSeparatorChar));
		directoryFile.mkdirs();
		return new File(directoryFile, filename);
	}

	public static void remove(String pathname) {
		File file = new File(pathname);
		if(file.exists()){
			file.delete();
		}
	}
	
	private TempFileUtils(){
	    
	}
}
