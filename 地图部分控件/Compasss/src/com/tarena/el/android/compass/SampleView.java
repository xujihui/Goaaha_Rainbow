package com.tarena.el.android.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class SampleView extends View {
	private Paint mPaint = new Paint();
	private Path mPath = new Path();
	private float[] mValues;
	
	public SampleView(Context context,AttributeSet attrs) {
		super(context,attrs);
		mPath.moveTo(0, -50);
		mPath.lineTo(-20, 60);
		mPath.lineTo(0, 50);
		mPath.lineTo(20, 60);
		mPath.close();

	}


	public void setValues(float[] mValues) {
		this.mValues = mValues;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = mPaint;
		paint.setColor(Color.WHITE);

		int w = canvas.getWidth();
		int h = canvas.getHeight();

		canvas.translate(w/ 2, h/ 2);
		
		if (mValues != null) {
			canvas.rotate(-mValues[0]);
		}
		canvas.drawPath(mPath, mPaint);
	}

}