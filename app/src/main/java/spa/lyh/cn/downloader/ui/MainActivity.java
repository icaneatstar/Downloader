package spa.lyh.cn.downloader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import spa.lyh.cn.downloader.R;
import spa.lyh.cn.lib_download.DownloadListener;
import spa.lyh.cn.lib_download.DownloadTask;


public class MainActivity extends Activity implements OnClickListener,
		DownloadListener {

	private TextView state;
	private ProgressBar progressBar;
	private DownloadTask downloadTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		state = (TextView) findViewById(R.id.state);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		findViewById(R.id.start).setOnClickListener(this);
		findViewById(R.id.pause).setOnClickListener(this);
		findViewById(R.id.delete).setOnClickListener(this);
	}

	@Override
	public void onDownloadStart(DownloadTask task) {
		state.setText(task.getName() + "==> start download"+" Status: "+task.getStatus());
		downloadTask = task;
		
	}

	@Override
	public void onDownloadUpdated(DownloadTask task, long finishedSize,
			long trafficSpeed) {
		// TODO Auto-generated method stub
		state.setText(task.getName() + "==> downloading..., speed: "
				+ trafficSpeed+" Status: "+task.getStatus());
		long total = task.getDownloadTotalSize();
		if (total != 0) {
			progressBar
					.setProgress((int) (task.getDownloadFinishedSize() * 100 / total));
		}
	}

	@Override
	public void onDownloadPaused(DownloadTask task) {
		state.setText(task.getName() + "==> pause download"+" Status: "+task.getStatus());
	}

	@Override
	public void onDownloadResumed(DownloadTask task) {
		state.setText(task.getName() + "==> resume download"+" Status: "+task.getStatus());

	}

	@Override
	public void onDownloadSuccessed(DownloadTask task) {
		state.setText(task.getName() + "==> download successed"+" Status: "+task.getStatus());

	}

	@Override
	public void onDownloadCanceled(DownloadTask task) {
		state.setText(task.getName() + "==> download canceled"+" Status: "+task.getStatus());
	}

	@Override
	public void onDownloadFailed(DownloadTask task) {
		state.setText(task.getName() + "==> download failed"+" Status: "+task.getStatus());
	}

	@Override
	public void onDownloadRetry(DownloadTask task) {
		state.setText(task.getName() + "==> retry download "+" Status: "+task.getStatus());
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.start:
//			if(downloadTask!=null&&downloadTask.getStatus()==DownloadTask.STATUS_PAUSED){
//				DownloadManager.getInstance().resumeDownload(downloadTask);
//			}else{
//				DownloadManager.getInstance().addDownloadTask(
//						SourceProvicer.getSimpleTask(), this);
//			}
			
			break;
		case R.id.pause:
//			if(downloadTask!=null){
//				DownloadManager.getInstance().pauseDownload(
//						downloadTask);
//			}
				
			break;
		case R.id.delete:
//			if(downloadTask!=null){
//				DownloadManager.getInstance().cancelDownload(
//						downloadTask);
//			}
			break;
		default:

			break;
		}
	}

}
