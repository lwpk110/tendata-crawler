package cn.xinbee.rcs.core.io.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ZipFileTool {
    
	private File zipFile;
	private List<File> files = new ArrayList<File>();
	
	public ZipFileTool(String pathname) {
		zipFile = new File(pathname);
	}
	
	public ZipFileTool(File file) {
		zipFile = file;
	}
	
	public ZipFileTool addFile(File file) {
		files.add(file);
		return this;
	}
	
	public ZipFileTool addFile(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			files.add(file);
		}
		return this;
	}
	
	public ZipFileTool addFilesInPath(String pathname) {
		File path = new File(pathname);
		if (path.isDirectory()) {
			for (File file : path.listFiles()) {
				files.add(file);
			}
		}
		return this;
	}
	
	public void compress() throws Exception {
		final int BUFFER_SIZE = 4096;
		ZipOutputStream out = new ZipOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(zipFile), 
						BUFFER_SIZE));
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream in;
		ZipEntry entry;
		int count;
		for (File file : files) {
			in = new FileInputStream(file);
			entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			out.closeEntry();
			in.close();
		}
		out.close();
	}
}
