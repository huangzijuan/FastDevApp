package com.example.cm.fastdevapp.test;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;

public class TestFile {
    private static final String TAG = "TestFile";

    public static void main(String[] args) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void test(Context context) {
        File fileDir = context.getFilesDir();
        Log.e(TAG, "fileDir " + fileDir.getAbsolutePath());  // /data/user/0/com.example.cm.fastdevapp/files

        File cacheDir = context.getCacheDir();
        Log.e(TAG, "cacheDir " + cacheDir.getAbsolutePath());  // /data/user/0/com.example.cm.fastdevapp/cache

//        File dataDir = context.getDataDir();
//        Log.e(TAG, "dataDir " + dataDir.getAbsolutePath());

        File workDir = context.getDir("workDir", Context.MODE_PRIVATE);
        Log.e(TAG, "workdir " + workDir.getAbsolutePath());     // /data/user/0/com.example.cm.fastdevapp/app_workDir

        File sdcardDir = Environment.getExternalStorageDirectory();
        Log.e(TAG, "sdcardDir " + sdcardDir.getAbsolutePath());      // /storage/emulated/0
    }
}
