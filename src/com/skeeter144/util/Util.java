package com.skeeter144.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

import org.osbot.utility.Logger;

import com.skeeter144.main.MainScript;

public class Util {

	public static void copyResourcesToDataDir() {
//		File resourcesDir = new File(OSBotDataDir() + "/SB300/res");
//			if (!resourcesDir.isDirectory()) {
//				copyDirectory(new File("/res"), resourcesDir);
//			}

	}

	public static String OSBotDataDir() {
		return System.getProperty("user.home") + File.separator + "OSBot" + File.separator + "Data";
	}

	public static void copyDirectory(File sourceDir, File targetDir) throws IOException {
		if (sourceDir.isDirectory()) {
			copyDirectoryRecursively(sourceDir, targetDir);
		} else {
			Files.copy(sourceDir.toPath(), targetDir.toPath());
		}
	}

	private static void copyDirectoryRecursively(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String child : source.list()) {
			copyDirectory(new File(source, child), new File(target, child));
		}
	}
	
	public static File getFileFromURL(ClassLoader cl, String fileName) {
	    URL url = cl.getResource(fileName);
	    File file = null;
	    try {
	        file = new File(url.toURI());
	    } catch (URISyntaxException e) {
	        file = new File(url.getPath());
	    }
	    return file;
	}
	
	static ArrayList<String> queuedMsgs = new ArrayList<>();
	public static void log(String msg) {
		Logger logger = MainScript.instance().logger;
		if(logger != null) {
			while(!queuedMsgs.isEmpty()) {
				logger.debug(queuedMsgs.remove(queuedMsgs.size() - 1));
			}
			logger.debug(msg);
		}else {
			queuedMsgs.add(0, msg);
		}
	}
}
