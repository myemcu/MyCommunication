package com.myemcu.app_9mycommunication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mPhonedit;
    private Button mPhonebda;
    private Button mButtonms;
    private EditText mMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        mPhonebda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + mPhonedit.getText().toString()); // 字符串转URI对象
                Intent intent = new Intent(Intent.ACTION_CALL, uri);

                // 这个if()是AS让加的，不然MainActivity.this.startActivity(intent);就有红浪。
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                MainActivity.this.startActivity(intent);
            }
        });

        mButtonms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile  = mPhonedit.getText().toString();    // 对方号码
                String content = mMess.getText().toString();        // 短信内容

                Intent intent  = new Intent();
                intent.setData(Uri.parse("smsto:"+mobile));          // 设置Intent数据
                intent.putExtra("sms_body",content);               // 存放短信内容
                startActivity(intent);

            }
        });
    }

    private void findViews() {
        mPhonedit = (EditText) findViewById(R.id.num);
        mPhonebda = (Button) findViewById(R.id.mybtn);
        mMess = (EditText) findViewById(R.id.mess);
        mButtonms = (Button) findViewById(R.id.btn);
    }
}
