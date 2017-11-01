package spa.lyh.cn.lib_download;


import spa.lyh.cn.lib_download.util.MD5;

public class MD5DownloadTaskIDCreator implements DownloadTaskIDCreator {

	@Override
	public String createId(DownloadTask task) {
		return MD5.getMD5(task.getUrl());
	}

}
