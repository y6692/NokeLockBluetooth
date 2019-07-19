package com.nokelock.nokelockbluetooth;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.fitsleep.sunshinelibrary.inter.OnItemAdapterClick;
import com.fitsleep.sunshinelibrary.utils.EncryptUtils;
import com.fitsleep.sunshinelibrary.utils.HexUtil;
import com.nokelock.nokelockbluetooth.adapter.DeviceListAdapter;
import com.nokelock.nokelockbluetooth.bean.CheckVersionBean;
import com.nokelock.nokelockbluetooth.bean.DeviceBean;
import com.nokelock.nokelockbluetooth.bean.VersionBean;
import com.nokelock.nokelockbluetooth.config.ApiManage;
import com.nokelock.nokelockbluetooth.utils.HttpHelper;
import com.nokelock.nokelockbluetooth.utils.ToolsUtils;
import com.sunshine.blelibrary.config.Config;
import com.sunshine.blelibrary.inter.OnDeviceSearchListener;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import http.RequestParams;
import http.TextHttpResponseHandler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


//入库
public class Main2Activity extends MPermissionsActivity {

    @BindView(R.id.tv_scan)
    TextView tv_scan;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.b1)
    Button b1;

    Context context;

    private BluetoothDevice mac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this;

        ButterKnife.bind(this);

        mac = getIntent().getParcelableExtra("mac");

        tv_scan.setText(""+mac.getAddress());
    }



    @OnClick(R.id.b1)
    void b1() {
        Log.e("main2===b1", mac.getAddress()+"==="+et.getText().toString());

        RequestParams params = new RequestParams();
        params.put("macinfo", mac.getAddress());
        params.put("deviceuuid", et.getText().toString());
        HttpHelper.post(context, "http://www.7mate.cn?g=App&m=Test&a=add_car",params,new TextHttpResponseHandler() {
            @Override
            public void onStart() {
//                if (loadingDialog != null && !loadingDialog.isShowing()){
//                    loadingDialog.show();
//                    loadingDialog.setTitle("正在提交");
//                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    ResultConsel result = JSON.parseObject(responseString, ResultConsel.class);

                    Log.e("b0===", "==="+responseString);

                    if (result.getFlag().equals("Success")) {
//                        PayMonthCartBean bean = JSON.parseObject(result.getData(),PayMonthCartBean.class);

                        Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();

                        Log.e("b1===", "===Success");

//                        type1Text.setText(bean.getMonth_money()+"元");
//                        type1Text2.setText(bean.getMonth_money_original()+"元");
//                        type1Text3.setText(bean.getMonth_money_discount());
//                        days1Text.setText("("+bean.getMonth_day()+"天不限次)");
//
//                        type2Text.setText(bean.getQuarter_money()+"元");
//                        type2Text2.setText(bean.getQuarter_money_original()+"元");
//                        type2Text3.setText(bean.getQuarter_money_discount());
//                        days2Text.setText("("+bean.getQuarter_day()+"天不限次)");
//
//                        type3Text.setText(bean.getWeek_money()+"元");
//                        days3Text.setText("("+bean.getWeek_day()+"天不限次)");
//
//                        moneyText.setText(type2Text.getText().toString().trim());
//                        daysText.setText(days2Text.getText().toString().trim());
//
//                        gamestatus = bean.getGamestatus();
//
//                        Log.e("userMonth===", "==="+bean.getGamestatus());

                    } else {
                        Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
//                    if (loadingDialog != null && loadingDialog.isShowing()){
//                        loadingDialog.dismiss();
//                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                if (loadingDialog != null && loadingDialog.isShowing()){
//                    loadingDialog.dismiss();
//                }
//                UIHelper.ToastError(context, throwable.toString());
            }
        });

    }

}
