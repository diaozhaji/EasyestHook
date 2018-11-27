package com.example.jy.hooktest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
            }
        });
    }

    private void startActivity() {
        //startActivity(new Intent(MainActivity.this, SecondActivity.class));
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        // 注意这里使用的ApplicationContext 启动的Activity
        // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
        // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
        // 比较简单, 直接替换这个Activity的此字段即可.
        getApplicationContext().startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            HookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
