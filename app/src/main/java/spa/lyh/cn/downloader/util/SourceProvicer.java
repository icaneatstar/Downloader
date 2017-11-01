package spa.lyh.cn.downloader.util;


import spa.lyh.cn.lib_download.DownloadTask;

public class SourceProvicer {

	public static final String APK_URL = "http://dldir1.qq.com/qqfile/qq/QQ6.1/11879/QQ6.1.exe";
	
	public static DownloadTask getSimpleTask() {
		DownloadTask task = new DownloadTask();
		task.setName("QQ");
		task.setUrl(APK_URL);
		
		return task;
	}
}
