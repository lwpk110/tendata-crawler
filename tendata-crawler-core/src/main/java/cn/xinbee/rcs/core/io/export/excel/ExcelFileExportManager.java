package cn.xinbee.rcs.core.io.export.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import cn.xinbee.rcs.core.io.FieldDescriptor;
import cn.xinbee.rcs.core.io.FieldDescriptorsResolver;
import cn.xinbee.rcs.core.io.util.TempFileUtils;
import cn.xinbee.rcs.core.io.util.ZipFileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;

import cn.xinbee.rcs.core.io.export.FileExportManager;
import cn.xinbee.rcs.core.io.export.FileExporter;

public class ExcelFileExportManager implements FileExportManager {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelFileExportManager.class);
	private static final AtomicInteger EXPORT_COUNTER = new AtomicInteger();
	
	private final FieldDescriptorsResolver fieldDescriptorsResolver;
	private final FileExporter fileExporter;
	private Resource attachmentsFileDir;
	
	private String tempDir = TempFileUtils.TEMP_DIR;
	
	public FileExporter getFileExporter() {
		return fileExporter;
	}
	
	public Resource getAttachmentsFileDir() {
		return attachmentsFileDir;
	}

	public void setAttachmentsFileDir(Resource attachmentsFileDir) {
		this.attachmentsFileDir = attachmentsFileDir;
	}
	
	public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public ExcelFileExportManager(FieldDescriptorsResolver fieldDescriptorsResolver, FileExporter fileExporter) {
        this.fieldDescriptorsResolver = fieldDescriptorsResolver;
        this.fileExporter = fileExporter;
    }

	private List<FieldDescriptor> getFieldDescriptors(Collection<?> dataCol, Locale locale){
		List<FieldDescriptor> descriptors = new ArrayList<FieldDescriptor>(0);
		if(dataCol != null && dataCol.size() > 0){
			descriptors = fieldDescriptorsResolver.resolve(dataCol.iterator().next(), locale);
		}
		return descriptors;
	}
	
	public String generateFilename(){
	    return String.format("%1$tY%1$tm%1$td_%1$tH%1$tM%1$tS_%2$d", 
                new Date(), EXPORT_COUNTER.incrementAndGet());
	}
	
	@Override
	public void export(OutputStream stream, Collection<?> dataCol, Locale locale) throws IOException {
		fileExporter.export(stream, dataCol, getFieldDescriptors(dataCol, locale));
	}
	
	@Override
	public File export(Collection<?> dataCol, Locale locale) throws IOException {
		return export(generateFilename(), dataCol, locale);
	}

	@Override
	public File export(String filename, Collection<?> dataCol, Locale locale) throws IOException {
		File file = TempFileUtils.generate(tempDir, new String[]{"excels"}, filename+".xls");
		fileExporter.export(file, dataCol, getFieldDescriptors(dataCol, locale));
		return file;
	}

	@Override
	public File exportAndCompress(Collection<?> dataCol, Locale locale) throws IOException {
		File excelFile = export(dataCol, locale);
		LOGGER.debug("export excel file: "+excelFile);
		
		File zipFile = TempFileUtils.generate(
		        tempDir,
				new String[]{"excels"},
				excelFile.getName().substring(0,excelFile.getName().lastIndexOf('.'))+".zip"
		);
		try {
			ZipFileTool zipFileTool = new ZipFileTool(zipFile);
			zipFileTool.addFile(excelFile);
			if(attachmentsFileDir != null){
				zipFileTool.addFilesInPath(attachmentsFileDir.getFile().getPath());
			}
			zipFileTool.compress();
			TempFileUtils.remove(excelFile.getPath());
		} catch (Exception e) {
		    LOGGER.error(e.getMessage(), e);
		}
		return zipFile;
	}

	@Override
	public void export(OutputStream stream, Collection<?> dataCol) throws IOException {
		export(stream, dataCol, LocaleContextHolder.getLocale());
	}

	@Override
	public File export(Collection<?> dataCol) throws IOException {
		return export(dataCol, LocaleContextHolder.getLocale());
	}

	@Override
	public File export(String filename, Collection<?> dataCol) throws IOException {
		return export(filename, dataCol, LocaleContextHolder.getLocale());
	}

	@Override
	public File exportAndCompress(Collection<?> dataCol) throws IOException {
		return exportAndCompress(dataCol, LocaleContextHolder.getLocale());
	}
}
