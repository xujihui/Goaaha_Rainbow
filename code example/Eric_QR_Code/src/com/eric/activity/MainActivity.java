package com.eric.activity;



import java.util.Hashtable;

import com.eric.qrcode.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private TextView text_id,text_loginpro,text_more;
	private Button btn_scan,btn_change;
	private ImageButton btn_reset;
	private EditText edit_password;
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		img = (ImageView) findViewById(R.id.img_touxiang);
		text_id = (TextView) findViewById(R.id.text_id);
		text_loginpro =(TextView) findViewById(R.id.text_loginpro);
		btn_change = (Button) findViewById(R.id.btn_change);
		btn_scan = (Button) findViewById(R.id.btn_scan);
		btn_reset = (ImageButton) findViewById(R.id.btn_reset);
		edit_password = (EditText) findViewById(R.id.edit_password);
		
		btn_scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, CaptureActivity.class);
				startActivity(intent);
			}
		}) ;
		
		btn_change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String code = edit_password.getText().toString();
					Bitmap codeBitmap = EncodingHandler.createQRCode(code,220);
					img.setImageBitmap(codeBitmap);
				} catch (WriterException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	

	//生成二维码
	public final static class EncodingHandler {   
	    private static final int BLACK = 0xff000000;   
	       
	    public static Bitmap createQRCode(String str,int widthAndHeight) throws WriterException {   
	        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();     
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");    
	        BitMatrix matrix = new MultiFormatWriter().encode(str,   
	                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);   
	        int width = matrix.getWidth();   
	        int height = matrix.getHeight();   
	        int[] pixels = new int[width * height];    
	           
	        for (int y = 0; y < height; y++) {   
	            for (int x = 0; x < width; x++) {   
	                if (matrix.get(x, y)) {   
	                    pixels[y * width + x] = BLACK;   
	                }   
	            }   
	        }   
	        Bitmap bitmap = Bitmap.createBitmap(width, height,   
	                Bitmap.Config.ARGB_8888);   
	        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);   
	        return bitmap;   
	    }   
	}   
}
