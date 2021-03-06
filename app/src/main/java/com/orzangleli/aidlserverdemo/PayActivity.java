package com.orzangleli.aidlserverdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>description：
 * <p>===============================
 * <p>creator：lixiancheng
 * <p>create time：2018/3/26 下午7:15
 * <p>===============================
 * <p>reasons for modification：
 * <p>Modifier：
 * <p>Modify time：
 * <p>@version
 */

public class PayActivity extends Activity {

    private TextView mTitleTv;
    private TextView mContentTv;
    private Button mSureBtn;
    private Button mCancelBtn;

    private OrderVo mOrderVo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_pay);

        mTitleTv = this.findViewById(R.id.title);
        mContentTv = this.findViewById(R.id.content);
        mSureBtn = this.findViewById(R.id.sure);
        mCancelBtn = this.findViewById(R.id.cancel);

        mOrderVo = this.getIntent().getParcelableExtra("orderVo");

        double oPrice = Double.parseDouble(mOrderVo.getPrice());
        double dPrice = Double.parseDouble(mOrderVo.getDiscount());

        double realPay = oPrice - dPrice;
        realPay = realPay < 0 ? 0 : realPay;

        mContentTv.setText(mOrderVo.getName() + "\n订单编号：" + mOrderVo.getId() + "\n订单金额：" + mOrderVo.getPrice() + "元\n折扣金额：" + mOrderVo.getDiscount() + "元" + "\n您需要支付"+realPay+"元，是否支付？");

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lxc", "支付成功 ---> ");
                Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                PayActivity.this.finish();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lxc", "支付取消 ---> ");
                Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_LONG).show();
                PayActivity.this.finish();
            }
        });

    }
}
