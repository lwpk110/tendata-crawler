package cn.xinbee.rcs.core.io.export;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Locale;

public interface FileExportManager {
    
	void export(OutputStream stream, Collection<?> dataCol, Locale locale) throws IOException;
	File export(Collection<?> dataCol, Locale locale) throws IOException;
	File export(String filename, Collection<?> dataCol, Locale locale) throws IOException;
	File exportAndCompress(Collection<?> dataCol, Locale locale) throws IOException;
	
	void export(OutputStream stream, Collection<?> dataCol) throws IOException;
	File export(Collection<?> dataCol) throws IOException;
	File export(String filename, Collection<?> dataCol) throws IOException;
	File exportAndCompress(Collection<?> dataCol) throws IOException;
}
