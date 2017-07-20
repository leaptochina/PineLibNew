package com.pine.lib.windows.alter.style;

import android.content.Context;
import android.os.Bundle;

import com.pine.lib.R;

public class IPhoneDialog extends AbsAlertDialog
{

	public IPhoneDialog(Context context, int theme)
	{
		super(context, theme);
	}


	public IPhoneDialog(Context context)
	{
		super(context);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_box_iphone);
		
	
	}
	
	public IPhoneDialog getView()
	{
		return this;
	}
	
}
