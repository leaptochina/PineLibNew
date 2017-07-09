package com.pine.lib.miui.flywindows;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.pine.lib.BuildConfig;
import com.pine.lib.base.activity.A;
import com.pine.lib.func.debug.G;

public class MIUIAuthorityAdmin {
	private static G g = new G(MIUIAuthorityAdmin.class);



	private static void gotoMiuiPermission() {
		Intent i = new Intent("miui.intent.action.APP_PERM_EDITOR");
		ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
		i.setComponent(componentName);
		i.putExtra("extra_pkgname", A.c().getPackageName());
		try {
			A.a().startActivity(i);
		} catch (Exception e) {
			e.printStackTrace();
			gotoMeizuPermission();
		}
	}

	private static void gotoMeizuPermission() {
		Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
		try {
			A.a().startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			gotoHuaweiPermission();
		}
	}


	/**
	 * 华为的权限管理页面
	 */
	private static void gotoHuaweiPermission() {
		try {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
			intent.setComponent(comp);
			A.a().startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			A.a().startActivity(getAppDetailSettingIntent());
		}

	}

	/**
	 * 获取应用详情页面intent
	 *
	 * @return
	 */
	private static Intent getAppDetailSettingIntent() {
		Intent localIntent = new Intent();
		localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (Build.VERSION.SDK_INT >= 9) {
			localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			localIntent.setData(Uri.fromParts("package", A.a().getPackageName(), null));
		} else if (Build.VERSION.SDK_INT <= 8) {
			localIntent.setAction(Intent.ACTION_VIEW);
			localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			localIntent.putExtra("com.android.settings.ApplicationPkgName", A.a().getPackageName());
		}
		return localIntent;
	}

	/**
	 * 打开MIUI权限管理界面(MIUI v5, v6)
	 * 输入必须为Activity
	 *
	 * @param context
	 */
	public static void openMiuiPermissionActivity(Activity context) {
		gotoMiuiPermission();

		return;

//		Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//
//
//		if (MIUISystemVersion.getSystemVersion() == 5) {
//			PackageInfo pInfo = null;
//			try {
//				pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
//						0);
//			} catch (NameNotFoundException e) {
//				g.e(e.toString());
//			}
//			intent.setClassName("SETTINGS_PACKAGE_NAME",
//					"com.miui.securitycenter.permission.AppPermissionsEditor");
//			intent.putExtra("extra_package_uid", pInfo.applicationInfo.uid);
//
//		} else if (MIUISystemVersion.getSystemVersion() == 6) {
//			intent.setClassName("com.miui.securitycenter",
//					"com.miui.permcenter.permissions.AppPermissionsEditorActivity");
//			intent.putExtra("extra_pkgname", context.getPackageName());
//		}
//
//		if (isIntentAvailable(context, intent)) {
//			if (context instanceof Activity) {
//				Activity a = (Activity) context;
//				a.startActivityForResult(intent, 2);
//			}
//		} else {
//			g.e("Intent is not available!");
//		}
	}
//
//	@TargetApi(9)
//	public static void openAppDetailActivity(Context context, String packageName) {
//		Intent intent = null;
//		if (Build.VERSION.SDK_INT >= 9) {
//			intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//			Uri uri = Uri.fromParts("SCHEME_PACKAGE", packageName, null);
//			intent.setData(uri);
//		} else {
//			final String className = Build.VERSION.SDK_INT == 8 ? "SETTINGS_APPDETAILS_CLASS_NAME_22"
//					: "SETTINGS_APPDETAILS_CLASS_NAME_B21";
//			intent = new Intent(Intent.ACTION_VIEW);
//			intent.setClassName("SETTINGS_PACKAGE_NAME",
//					"SETTINGS_APPDETAILS_CLASS_NAME");
//			intent.putExtra(className, packageName);
//		}
//		if (isIntentAvailable(context, intent)) {
//			context.startActivity(intent);
//		} else {
//			g.e("intent is not available!");
//		}
//	}

//	/**
//	 * 判断是否有可以接受的Activity
//	 *
//	 * @param context
//	 * @param action
//	 * @return
//	 */
//	public static boolean isIntentAvailable(Context context, Intent intent) {
//		if (intent == null)
//			return false;
//		return context.getPackageManager()
//				.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES)
//				.size() > 0;
//	}
}
