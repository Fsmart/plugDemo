package com.ggp.plugdemo.plugdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "plugTest";
    private Button mButton;
    private static final int SD_READ = 101;
    private static final int SD_WRITE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissionForNormal();
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v->loadPlug());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    //获取权限
    private void checkPermissionForNormal() {
        //判断是否同意此权限
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "已授权sd卡", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.sd_card), SD_READ, perms);
        }


    }

    public void loadPlug() {
        String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/Test.apk");
        Log.e(TAG, "pluginPath: " + pluginPath);
        File plugin = new File(pluginPath);
        try {
            PluginManager.getInstance(getApplicationContext()).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();
        intent.setClassName("com.ggp.plugdemo.plugapk.plugapk", "com.ggp.plugdemo.plugapk.plugapk.MainActivity");
        startActivity(intent);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}
