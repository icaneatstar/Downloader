package spa.lyh.cn.downloader.ui;


import spa.lyh.cn.lib_download.DownloadTask;
import spa.lyh.cn.lib_download.DownloadTaskIDCreator;

public class IDCreate implements DownloadTaskIDCreator {

	@Override
	public String createId(DownloadTask task) {
		// TODO Auto-generated method stub
		return task.getId();
	}

}
