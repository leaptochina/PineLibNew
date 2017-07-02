import com.hslh.mytool.KillProcessServer;
import com.hslh.mytool.KillProcessServer1;
import com.hslh.mytool.R;
import com.qianfan365.lib.func.debug.G;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

Android之Widget学习总结

1、widget 声明 在AndroidManifest.xml的Application标签下


	<receiver
	android:name=".KillProcessServer" //widget的显示类继承 AppWidgetProvider
	android:label="@string/app_name" >
		<intent-filter>
		    <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
		</intent-filter>
		
		<meta-data
		    android:name="android.appwidget.provider"
		    android:resource="@xml/widget" />//这个是widget的更新相关声明
	</receiver>


	<receiver
    android:name=".KillProcessServer1"//这个widget点击后会发送这个广播 广播的处理类 继承BroadcastReceiver
    android:exported="false" //这句话不允许外部访问>
	    <intent-filter>
	        <action android:name="CHANGE_MAIN_BUTTON_CONTENT" />//广播的内容
	    </intent-filter>
	</receiver>

2、创建res\xml目录
3、创建文件widget.xml这个是widget的更新声明 在AndroidManifest.xml的receiver中修改名字为@xml/widget
widget.xml文件如下

	<?xml version="1.0" encoding="utf-8"?>
	<appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
	    android:minWidth="72dp"//(74 * 1) - 2
	    android:minHeight="72dp"
	    android:updatePeriodMillis="86400000"//更新周期 单位ms
	    android:initialLayout="@layout/widget_icon" >//这个是布局
	</appwidget-provider>

4、创建布局文件widget_icon.xml
	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="@dimen/w20b"
	    android:layout_height="@dimen/h15b"
	    android:orientation="vertical" >
	
	    <Button
	        android:id="@+id/widgetText"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:text="Button"
	        android:textSize="@dimen/f100k"
	        android:textColor="#000000" />
	
	</LinearLayout>

5、创建核心代码 KillProcessServer.java

public class KillProcessServer extends AppWidgetProvider
{
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		RemoteViews updateView = new RemoteViews(context.getPackageName(),
				R.layout.widget_icon);
		String ii = String.valueOf(i++);
		Toast.makeText(context, "初始化Widget", Toast.LENGTH_LONG).show();
		updateView.setTextViewText(R.id.widgetText, ii);


		Intent intent2 = new Intent("CHANGE_MAIN_BUTTON_CONTENT");
		PendingIntent pending2 = PendingIntent.getBroadcast(context, 0,
				intent2, 0);// getActivity
		updateView.setOnClickPendingIntent(R.id.widgetText, pending2);

		appWidgetManager.updateAppWidget(appWidgetIds, updateView);

		
	}

}

6、创建KillProcessServer1.java
public class KillProcessServer1 extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{

		LayoutInflater flater = LayoutInflater.from(context);
		View view = flater.inflate(R.layout.widget_icon, null);
		Button btn = (Button) view.findViewById(R.id.widgetText);
		System.out.println(btn.getText());
		if (intent.getAction().equals("CHANGE_MAIN_BUTTON_CONTENT"))
		{
			Toast.makeText(context, "123", Toast.LENGTH_LONG).show();
			btn.setText("haha");
			System.out.println(btn.getText());
		}
	}

}








