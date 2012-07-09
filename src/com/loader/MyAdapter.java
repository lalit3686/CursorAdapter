package com.loader;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyAdapter extends CursorAdapter{

	Cursor c;
	LayoutInflater inflater;
	Context context;
	private String TAG = getClass().getSimpleName();
	Helper helper;
	
	public MyAdapter(Context context, Cursor c) {
		super(context, c);
		
		this.c = c;
		this.context = context;
		inflater = LayoutInflater.from(context);
		helper = new Helper(context);
	}
	
	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		
		TextView txtName = (TextView) view.findViewById(R.id.txt_name);
		txtName.setText(cursor.getString(cursor.getColumnIndex(Helper.tbl_col_username)));
		TextView txtPassword = (TextView) view.findViewById(R.id.txt_password);
		txtPassword.setText(cursor.getString(cursor.getColumnIndex(Helper.tbl_col_password)));
		
		Button button = (Button) view.findViewById(R.id.btn_delete);
		final String dataId = cursor.getString(cursor.getColumnIndex(Helper.tbl_col_id));
		
		final Builder builder = new Builder(this.context);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Log.d(TAG, "Button Click "+dataId);
				
				builder.setTitle("Are you sure you want to delete?");
				builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						helper.delete(dataId);
						c.requery();
						notifyDataSetChanged();
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// nothing
					}
				});
				builder.create().show();
			}
		});
	}
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		View v = inflater.inflate(R.layout.row, null); 
		return v;
	}
}
