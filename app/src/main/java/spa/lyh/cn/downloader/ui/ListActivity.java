package spa.lyh.cn.downloader.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import spa.lyh.cn.downloader.AppContext;
import spa.lyh.cn.downloader.R;
import spa.lyh.cn.lib_download.DownloadListener;
import spa.lyh.cn.lib_download.DownloadTask;

public class ListActivity extends Activity implements DownloadListener {
	private ListView listView;
	private Button addBtn;
	private ListAdapter adapter ;
//	private List<GameInfoModel> list;
	private List<DownloadTask> list;
	private int ids;
	private String[] urls={
			"http://dldir1.qq.com/qqfile/qq/QQ6.1/11879/QQ6.1.exe",
			"http://dlied6.qq.com/invc/xfspeed/qqpcmgr/versetup/portal/PCMgr_Setup_10_0_15100_225.exe",
			"http://dldir1.qq.com/music/clntupate/QQMusic_Setup_1110.exe",
			"http://dldir1.qq.com/invc/tt/QQBrowserSetup.exe",
			"http://dldir1.qq.com/qqfile/tm/TM2013Preview1.exe",
			"http://dldir1.qq.com/invc/cyclone/QQDownload_Setup_45_757_400.exe"};
	private String[] names={
			"QQ6.1",
			"QQ管家",
			"QQ音乐",
			"QQ浏览器",
			"TM2013",
			"QQ旋风"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		initViews();
		initData();
	}
	private void initData() {
//		list = new ArrayList<GameInfoModel>();
//		for(int i = 0;i<5;i++){
//			GameInfoModel model = new GameInfoModel();
//			model.setId(i+"");
//			model.setName(names[i]);
//			model.setUrl(urls[i]);
//			list.add(model);
//		}
//		adapter = new ListAdapter(list, this,AppContext.downloadMgr, getApplicationContext());
//		listView.setAdapter(adapter);
//		list = AppContext.downloadMgr.getAllDownloadTask();
		adapter = new ListAdapter(AppContext.downloadMgr.getAllDownloadTask(), this, AppContext.downloadMgr, getApplicationContext());
		listView.setAdapter(adapter);
	}
	private void initViews() {
		listView =(ListView)findViewById(R.id.download_list_LV);
		addBtn=(Button)findViewById(R.id.download_add_Btn);
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					ids++;
					DownloadTask task = new DownloadTask();
					task.setId(ids+"");
					task.setName(names[ids]);
					task.setUrl(urls[ids]);
					adapter.addItem(task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				
			}
		});
	}
	@Override
	public void onDownloadStart(DownloadTask task) {
		// TODO Auto-generated method stub
		adapter.notifyDataSetChanged();
//		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
//		startBtn.setText("Pause");
		
	}
	@Override
	public void onDownloadUpdated(DownloadTask task, long finishedSize,
			long trafficSpeed) {
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setText("Pause");
		TextView speedTV = (TextView)listView.findViewWithTag((task.getUrl()+"speed"));
		speedTV.setText((trafficSpeed/1024)+"KB/S  "+finishedSize+"/"+task.getDownloadTotalSize());
		int progress = (int)((task.getDownloadFinishedSize()*100)/task.getDownloadTotalSize());
		((ProgressBar)listView.findViewWithTag(task.getUrl())).setProgress(progress);
	}
	@Override
	public void onDownloadPaused(DownloadTask task) {
		// TODO Auto-generated method stub
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setText("Resume");
		
	}
	@Override
	public void onDownloadResumed(DownloadTask task) {
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setText("Pause");
		
	}
	@Override
	public void onDownloadSuccessed(DownloadTask task) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), task.getDownloadSavePath(), Toast.LENGTH_SHORT).show();
		Log.e("Sucessed","Name: "+task.getName()+" SavePath: "+task.getDownloadSavePath());
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setText("Install");
		Button stopBtn = (Button)listView.findViewWithTag((task.getUrl()+"stop"));
		stopBtn.setText("Delete");
		
	}
	@Override
	public void onDownloadCanceled(DownloadTask task) {
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setText("Start");
		adapter.removeItem(task);
		adapter.notifyDataSetChanged();
//		listView.notifyAll();
	}
	@Override
	public void onDownloadFailed(DownloadTask task) {
		Button startBtn = (Button)listView.findViewWithTag((task.getUrl()+"start"));
		startBtn.setClickable(false);
		startBtn.setText("Error");
		Button stopBtn = (Button)listView.findViewWithTag((task.getUrl()+"stop"));
		stopBtn.setText("Delete");
		
	}
	@Override
	public void onDownloadRetry(DownloadTask task) {
		// TODO Auto-generated method stub
		
	}

	
}
