package com.digitalchina.webview.activirty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalchina.webview.R;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * @author renjwb
 */
public class ReadFileByTBSActivity extends AppCompatActivity implements TbsReaderView.ReaderCallback {
    private FrameLayout mFrameLayout;
    private TbsReaderView mTbsReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbsview);
        mFrameLayout = findViewById(R.id.fl_file);
        mTbsReaderView = new TbsReaderView(this, this);
        mFrameLayout.addView(mTbsReaderView);
        initReadFile();
    }

    private void initReadFile() {
        String bsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsTempPath";
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            bsReaderTempFile.mkdir();
        }

        Intent intent = getIntent();
        String filePath = intent.getStringExtra("filePath");
        System.out.println("filePath@@@"+filePath);

        File file = new File(filePath);
        if (file.exists()){
            Log.e("exist!","File is exists.");
        }else {
            Toast.makeText(this, "File is not exist!", Toast.LENGTH_SHORT);
        }

        //通过bundle把文件传给x5,打开的事情交由x5处理
        Bundle bundle = new Bundle();
        bundle.putString("filePath", filePath);
        bundle.putString("tempPath", bsReaderTemp);
        //加载文件前的初始化工作,加载支持不同格式的插件
        boolean b = mTbsReaderView.preOpen(getFileType(filePath), false);
        if (b) {
            mTbsReaderView.openFile(bundle);
        }
    }

    /***
     * 获取文件类型
     *
     * @param path 文件路径
     * @return 文件的格式
     */
    private String getFileType(String path) {
        String str = "";

        if (TextUtils.isEmpty(path)) {
            return str;
        }
        int i = path.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }
        str = path.substring(i + 1);
        return str;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

}
