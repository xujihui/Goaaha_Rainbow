package com.easemob.chatuidemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.easemob.chatuidemo.R;

/**
 * Created by lenovo on 2017/6/10.
 */
public class MessageActivity extends Activity implements View.OnClickListener {
    private ImageView return_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        setListener();
    }
    private void initView() {
        return_back = (ImageView)findViewById(R.id.czh_back);
    }
    public void setListener() {
        return_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.czh_back:
                //转到我的资料页面
                Utils.flag = 5;
                Intent intent = new Intent();
                intent.setClass(MessageActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
