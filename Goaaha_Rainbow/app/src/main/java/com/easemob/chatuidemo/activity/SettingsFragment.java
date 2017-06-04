/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easemob.chatuidemo.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.Constant;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.R;


/**
 * 个人中心
 *
 * @author Administrator
 *
 */
public class SettingsFragment extends Fragment  {
	/**
	 * 用户头像
	 */
	private ImageView head_portrait;

	/**
	 * 个性签名
	 */
	private TextView tv_gxqm;

	/**
	 * 设置个性签名
	 */
	private RelativeLayout rl_edit_gxqm;

	/**
	 * 获取二维码
	 */
	private RelativeLayout rl_get_ewm;

	/**
	 * 分享
	 */
	private RelativeLayout rl_to_share;

	/**
	 * 注销账号
	 */
	private RelativeLayout rl_to_zx;

	/**
	 * 设置
	 */
	private RelativeLayout rl_to_edit;

	private Context context; //上下文环境

	/**
	 * 退出按钮
	 */
	private Button logoutBtn;
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
			return;
		initView();
		setListener();
		if(!TextUtils.isEmpty(EMChatManager.getInstance().getCurrentUser())){
			logoutBtn.setText(getString(R.string.button_logout) + "(" + EMChatManager.getInstance().getCurrentUser() + ")");
		}


	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//加载当前fragment的布局文件
		View cuttentView = inflater.inflate(R.layout.fragment_conversation_settings, container, false);
		// 返回（view）
		return cuttentView;
	}

	//获取控件
	public void initView(){
		head_portrait = (ImageView)getView().findViewById(R.id.head_portrait);
		tv_gxqm = (TextView) getView().findViewById(R.id.czh_gxqm);
		rl_edit_gxqm = (RelativeLayout) getView().findViewById(R.id.czh_edit_gxqm);
		rl_get_ewm = (RelativeLayout) getView().findViewById(R.id.czh_get_ewm);
		rl_to_share = (RelativeLayout) getView().findViewById(R.id.czh_to_share);
		rl_to_zx = (RelativeLayout) getView().findViewById(R.id.czh_to_zx);
		rl_to_edit = (RelativeLayout) getView().findViewById(R.id.czh_to_edit);
		logoutBtn = (Button) getView().findViewById(R.id.btn_logout);
	}

	public void setListener() {
		//设置头像
		head_portrait.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), Information.class);
				startActivity(intent);
			}
		});

		//设置个性签名
		rl_edit_gxqm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder Ad = new AlertDialog.Builder(getActivity());
				Ad.setTitle("请输入：");
				LinearLayout dialogLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_gxqm, null);
				Ad.setView(dialogLayout);
				final EditText et_gxqm = (EditText)dialogLayout.findViewById(R.id.gxqm);
				Ad.setPositiveButton("保存",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialogInterface,int i){
						String gxqm = et_gxqm.getText().toString();
						tv_gxqm.setText(gxqm);
						Toast.makeText(getActivity(),"设置成功",Toast.LENGTH_SHORT).show();
					}
				});
				Ad.setNegativeButton("取消", null);
				Ad.create();
				Ad.show();
			}
		});

		//获取二维码
		rl_get_ewm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		//分享
		rl_to_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		//注销
		rl_to_zx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		//设置
		rl_to_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(),SettingActivity.class);
				startActivity(intent);
			}
		});

		//退出登录
		logoutBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				logout();
			}
		});

	}

	//退出登录
	void logout() {
		final ProgressDialog pd = new ProgressDialog(getActivity());
		String st = getResources().getString(R.string.Are_logged_out);
		pd.setMessage(st);
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		DemoApplication.getInstance().logout(new EMCallBack() {

			@Override
			public void onSuccess() {
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						// 重新显示登陆页面
						((MainActivity) getActivity()).finish();
						startActivity(new Intent(getActivity(), LoginActivity.class));

					}
				});
			}

			@Override
			public void onProgress(int progress, String status) {

			}

			@Override
			public void onError(int code, String message) {

			}
		});
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(((MainActivity)getActivity()).isConflict){
			outState.putBoolean("isConflict", true);
		}else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
			outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
		}
	}


}
