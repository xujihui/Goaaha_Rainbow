package com.easemob.chatuidemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.EMCallBack;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.DemoHXSDKModel;
import com.easemob.chatuidemo.R;

public class SettingActivity extends Activity implements OnClickListener {
    /**
     * 返回个人页面
     */
    private ImageView iv_back_personal;

    /**
     * 设置新消息通知布局
     */
    private RelativeLayout rl_switch_notification;
    /**
     * 设置声音布局
     */
    private RelativeLayout rl_switch_sound;
    /**
     * 设置震动布局
     */
    private RelativeLayout rl_switch_vibrate;
    /**
     * 设置扬声器布局
     */
    private RelativeLayout rl_switch_speaker;

    /**
     * 打开新消息通知imageView
     */
    private ImageView iv_switch_open_notification;
    /**
     * 关闭新消息通知imageview
     */
    private ImageView iv_switch_close_notification;
    /**
     * 打开声音提示imageview
     */
    private ImageView iv_switch_open_sound;
    /**
     * 关闭声音提示imageview
     */
    private ImageView iv_switch_close_sound;
    /**
     * 打开消息震动提示
     */
    private ImageView iv_switch_open_vibrate;
    /**
     * 关闭消息震动提示
     */
    private ImageView iv_switch_close_vibrate;
    /**
     * 打开扬声器播放语音
     */
    private ImageView iv_switch_open_speaker;
    /**
     * 关闭扬声器播放语音
     */
    private ImageView iv_switch_close_speaker;

    /**
     * 声音和震动中间的那条线
     */
    private TextView textview1, textview2;

    private LinearLayout blacklistContainer;

    private RelativeLayout rl_switch_chatroom_leave;
    private ImageView iv_switch_room_owner_leave_allow;
    private ImageView iv_switch_room_owner_leave_disallow;

    private EMChatOptions chatOptions;
    private DemoHXSDKModel model;

    /**
     * 诊断
     */
    private LinearLayout llDiagnose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        initView();
        setListener();
    }
    //获取界面控件
    private void initView() {
        iv_back_personal = (ImageView)findViewById(R.id.czh_back);
        rl_switch_notification = (RelativeLayout) findViewById(R.id.rl_switch_notification);
        rl_switch_sound = (RelativeLayout) findViewById(R.id.rl_switch_sound);
        rl_switch_vibrate = (RelativeLayout) findViewById(R.id.rl_switch_vibrate);
        rl_switch_speaker = (RelativeLayout) findViewById(R.id.rl_switch_speaker);
        rl_switch_chatroom_leave = (RelativeLayout) findViewById(R.id.rl_switch_chatroom_owner_leave);

        iv_switch_open_notification = (ImageView) findViewById(R.id.iv_switch_open_notification);
        iv_switch_close_notification = (ImageView) findViewById(R.id.iv_switch_close_notification);
        iv_switch_open_sound = (ImageView) findViewById(R.id.iv_switch_open_sound);
        iv_switch_close_sound = (ImageView) findViewById(R.id.iv_switch_close_sound);
        iv_switch_open_vibrate = (ImageView) findViewById(R.id.iv_switch_open_vibrate);
        iv_switch_close_vibrate = (ImageView) findViewById(R.id.iv_switch_close_vibrate);
        iv_switch_open_speaker = (ImageView) findViewById(R.id.iv_switch_open_speaker);
        iv_switch_close_speaker = (ImageView) findViewById(R.id.iv_switch_close_speaker);
        iv_switch_room_owner_leave_allow = (ImageView) findViewById(R.id.iv_switch_chatroom_owner_leave_allow);
        iv_switch_room_owner_leave_disallow = (ImageView) findViewById(R.id.iv_switch_chatroom_owner_leave_not_allow);

        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);

        blacklistContainer = (LinearLayout) findViewById(R.id.ll_black_list);
        llDiagnose=(LinearLayout) findViewById(R.id.ll_diagnose);
    }

    //设置点击事件
    public void setListener() {
        iv_back_personal.setOnClickListener(this);
        blacklistContainer.setOnClickListener(this);
        rl_switch_notification.setOnClickListener(this);
        rl_switch_sound.setOnClickListener(this);
        rl_switch_vibrate.setOnClickListener(this);
        rl_switch_speaker.setOnClickListener(this);
        llDiagnose.setOnClickListener(this);
        rl_switch_chatroom_leave.setOnClickListener(this);

        chatOptions = EMChatManager.getInstance().getChatOptions();

        model = (DemoHXSDKModel) HXSDKHelper.getInstance().getModel();

        // 震动和声音总开关，来消息时，是否允许此开关打开
        // the vibrate and sound notification are allowed or not?
        if (model.getSettingMsgNotification()) {
            iv_switch_open_notification.setVisibility(View.VISIBLE);
            iv_switch_close_notification.setVisibility(View.INVISIBLE);
        } else {
            iv_switch_open_notification.setVisibility(View.INVISIBLE);
            iv_switch_close_notification.setVisibility(View.VISIBLE);
        }

        // 是否打开声音
        // sound notification is switched on or not?
        if (model.getSettingMsgSound()) {
            iv_switch_open_sound.setVisibility(View.VISIBLE);
            iv_switch_close_sound.setVisibility(View.INVISIBLE);
        } else {
            iv_switch_open_sound.setVisibility(View.INVISIBLE);
            iv_switch_close_sound.setVisibility(View.VISIBLE);
        }

        // 是否打开震动
        // vibrate notification is switched on or not?
        if (model.getSettingMsgVibrate()) {
            iv_switch_open_vibrate.setVisibility(View.VISIBLE);
            iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
        } else {
            iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
            iv_switch_close_vibrate.setVisibility(View.VISIBLE);
        }

        // 是否打开扬声器
        // the speaker is switched on or not?
        if (model.getSettingMsgSpeaker()) {
            iv_switch_open_speaker.setVisibility(View.VISIBLE);
            iv_switch_close_speaker.setVisibility(View.INVISIBLE);
        } else {
            iv_switch_open_speaker.setVisibility(View.INVISIBLE);
            iv_switch_close_speaker.setVisibility(View.VISIBLE);
        }

        // 是否允许聊天室owner leave
        if(model.isChatroomOwnerLeaveAllowed()){
            iv_switch_room_owner_leave_allow.setVisibility(View.VISIBLE);
            iv_switch_room_owner_leave_disallow.setVisibility(View.INVISIBLE);
        }else{
            iv_switch_room_owner_leave_allow.setVisibility(View.INVISIBLE);
            iv_switch_room_owner_leave_disallow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.czh_back:
                //转到我的资料页面
                Utils.flag = 5;
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_switch_notification:
                if (iv_switch_open_notification.getVisibility() == View.VISIBLE) {
                    iv_switch_open_notification.setVisibility(View.INVISIBLE);
                    iv_switch_close_notification.setVisibility(View.VISIBLE);
                    rl_switch_sound.setVisibility(View.GONE);
                    rl_switch_vibrate.setVisibility(View.GONE);
                    textview1.setVisibility(View.GONE);
                    textview2.setVisibility(View.GONE);
                    chatOptions.setNotificationEnable(false);
                    EMChatManager.getInstance().setChatOptions(chatOptions);

                    HXSDKHelper.getInstance().getModel().setSettingMsgNotification(false);
                } else {
                    iv_switch_open_notification.setVisibility(View.VISIBLE);
                    iv_switch_close_notification.setVisibility(View.INVISIBLE);
                    rl_switch_sound.setVisibility(View.VISIBLE);
                    rl_switch_vibrate.setVisibility(View.VISIBLE);
                    textview1.setVisibility(View.VISIBLE);
                    textview2.setVisibility(View.VISIBLE);
                    chatOptions.setNotificationEnable(true);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgNotification(true);
                }
                break;
            case R.id.rl_switch_sound:
                if (iv_switch_open_sound.getVisibility() == View.VISIBLE) {
                    iv_switch_open_sound.setVisibility(View.INVISIBLE);
                    iv_switch_close_sound.setVisibility(View.VISIBLE);
                    chatOptions.setNoticeBySound(false);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgSound(false);
                } else {
                    iv_switch_open_sound.setVisibility(View.VISIBLE);
                    iv_switch_close_sound.setVisibility(View.INVISIBLE);
                    chatOptions.setNoticeBySound(true);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgSound(true);
                }
                break;
            case R.id.rl_switch_vibrate:
                if (iv_switch_open_vibrate.getVisibility() == View.VISIBLE) {
                    iv_switch_open_vibrate.setVisibility(View.INVISIBLE);
                    iv_switch_close_vibrate.setVisibility(View.VISIBLE);
                    chatOptions.setNoticedByVibrate(false);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(false);
                } else {
                    iv_switch_open_vibrate.setVisibility(View.VISIBLE);
                    iv_switch_close_vibrate.setVisibility(View.INVISIBLE);
                    chatOptions.setNoticedByVibrate(true);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
                }
                break;
            case R.id.rl_switch_speaker:
                if (iv_switch_open_speaker.getVisibility() == View.VISIBLE) {
                    iv_switch_open_speaker.setVisibility(View.INVISIBLE);
                    iv_switch_close_speaker.setVisibility(View.VISIBLE);
                    chatOptions.setUseSpeaker(false);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgSpeaker(false);
                } else {
                    iv_switch_open_speaker.setVisibility(View.VISIBLE);
                    iv_switch_close_speaker.setVisibility(View.INVISIBLE);
                    chatOptions.setUseSpeaker(true);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    HXSDKHelper.getInstance().getModel().setSettingMsgVibrate(true);
                }
                break;
            case R.id.rl_switch_chatroom_owner_leave:
                if(this.iv_switch_room_owner_leave_allow.getVisibility() == View.VISIBLE){
                    iv_switch_room_owner_leave_allow.setVisibility(View.INVISIBLE);
                    iv_switch_room_owner_leave_disallow.setVisibility(View.VISIBLE);
                    chatOptions.allowChatroomOwnerLeave(false);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    model.allowChatroomOwnerLeave(false);

                }else{
                    iv_switch_room_owner_leave_allow.setVisibility(View.VISIBLE);
                    iv_switch_room_owner_leave_disallow.setVisibility(View.INVISIBLE);
                    chatOptions.allowChatroomOwnerLeave(true);
                    EMChatManager.getInstance().setChatOptions(chatOptions);
                    model.allowChatroomOwnerLeave(true);
                }
                break;

            case R.id.ll_black_list:
                startActivity(new Intent(this, BlacklistActivity.class));
                break;
            case R.id.ll_diagnose:
                startActivity(new Intent(this, DiagnoseActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
