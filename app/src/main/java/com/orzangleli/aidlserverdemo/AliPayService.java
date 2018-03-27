package com.orzangleli.aidlserverdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * <p>description：模拟支付宝的支付服务，client端吊起服务，并传入金额，支付环节由本Service执行，执行完毕后将支付结果返回给Client端。
 * <p>===============================
 * <p>creator：lixiancheng
 * <p>create time：2018/3/26 下午5:30
 * <p>===============================
 * <p>reasons for modification：
 * <p>Modifier：
 * <p>Modify time：
 * <p>@version
 */

public class AliPayService extends Service{

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IAliPayInterface.Stub mBinder = new IAliPayInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public boolean pay(OrderVo orderVo) throws RemoteException {
            Intent intent = new Intent(AliPayService.this, PayActivity.class);
            intent.putExtra("orderVo", orderVo);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            AliPayService.this.startActivity(intent);
            return true;
        }
    };

}
