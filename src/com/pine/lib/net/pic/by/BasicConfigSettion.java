package com.pine.lib.net.pic.by;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.pine.lib.R;
import com.pine.lib.net.pic.SetPicInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class BasicConfigSettion implements SetPicInterface
{
	public static int loading = R.drawable.loading_static;
	public static int errPic = R.drawable.pic_lost;
	public static int decodErr = R.drawable.error;



	/**
	 * 设置加载中的图标资源图标
	 */
	@Override
	public SetPicInterface setLoadingPic(int resId)
	{
		this.loading = resId;
		return this;
	}

	@Override
	public Boolean setPic(ImageView imageView, String localUrl) {
		try {
			FileInputStream fis = new FileInputStream(localUrl);
			imageView.setImageBitmap(BitmapFactory.decodeStream(fis));  ///把流转化为Bitmap图片
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}


	public int getLoading()
	{
		return loading;
	}


	public int getErrPic()
	{
		return errPic;
	}


	public void setErrPic(int errPic)
	{
		this.errPic = errPic;
	}


	public int getDecodErr()
	{
		return decodErr;
	}


	public void setDecodErr(int decodErr)
	{
		this.decodErr = decodErr;
	}

}
