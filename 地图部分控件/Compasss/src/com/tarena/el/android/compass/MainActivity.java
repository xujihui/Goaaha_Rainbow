package com.tarena.el.android.compass;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
/**
 * 指南针
 * 此程序使用手机传感器来显示指南针，当用户移动手机方向时，让指南针始终指向南方。
 * @author tangliang
 *
 */
public class MainActivity extends Activity {

	private SensorManager mSensorManager;
	private Sensor mSensor;
	private SampleView mView;
	private Button mBtn;
	float[] mValues;
	private final SensorEventListener mListener = new SensorEventListener() {
		public void onSensorChanged(SensorEvent event) {
			mValues = event.values;
			if (mView != null) {
				mView.setValues(mValues);
				mView.invalidate();
			}

			if (mBtn != null) {

				float direction = mValues[0];
				if (direction > 22.5f && direction < 157.5f) {
					// east
					mBtn.setText("东");
				} else if (direction > 202.5f && direction < 337.5f) {
					// west
					mBtn.setText("西");
				}else if (direction > 112.5f && direction < 247.5f) {
					// south
					mBtn.setText("南");
				} else if (direction < 67.5 || direction > 292.5f) {
					// north
					mBtn.setText("北");
				}
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		mView = (SampleView) findViewById(R.id.sampleview01);
		mBtn = (Button) findViewById(R.id.button01);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mListener, mSensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mSensorManager.unregisterListener(mListener);

	}

}
