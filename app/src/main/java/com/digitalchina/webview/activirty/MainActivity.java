package com.digitalchina.webview.activirty;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.digitalchina.webview.R;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity {

    private Button wordBtu,excelBtu,pptBtu,pdfBtu,mupdf_btu;
    private Button tbs_word,tbs_ppt,tbs_pdf,tbs_excel;

    private static final int RC_WRITE_STOREGE = 1;
    private static final String TAG = "TBSInit";

    String url;
    String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordBtu = findViewById(R.id.word_btu);
        pptBtu = findViewById(R.id.ppt_btu);
        pdfBtu = findViewById(R.id.pdf_btu);
        excelBtu = findViewById(R.id.excel_btu);
        tbs_word = findViewById(R.id.tbs_word_btu);
        tbs_excel = findViewById(R.id.tbs_excel_btu);
        tbs_pdf = findViewById(R.id.tbs_pdf_btu);
        tbs_ppt = findViewById(R.id.tbs_ppt_btu);
        mupdf_btu = findViewById(R.id.mupdf_btu);

        pptBtu.setOnClickListener(new ButtonListener());
        excelBtu.setOnClickListener(new ButtonListener());
        wordBtu.setOnClickListener(new ButtonListener());
        pdfBtu.setOnClickListener(new ButtonListener());
        tbs_word.setOnClickListener(new ButtonListener());
        tbs_excel.setOnClickListener(new ButtonListener());
        tbs_pdf.setOnClickListener(new ButtonListener());
        tbs_ppt.setOnClickListener(new ButtonListener());
        mupdf_btu.setOnClickListener(new ButtonListener());

        requestPermissions();
    }


    private void requestPermissions() {
        String[] perms = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        };
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_WRITE_STOREGE, perms)
                            .setRationale("请求权限")
                            .setPositiveButtonText("确认")
                            .setNegativeButtonText("取消")
                            .build());
        }
    }


    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()){
                case R.id.excel_btu:
                    url = "http://ow365.cn/?i=22605&furl=http://rjwlsy.top/DownloadFile/test.xlsx";
                    intent.setClass(MainActivity.this,WebviewActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    break;
                case  R.id.ppt_btu:
                    url = "http://ow365.cn/?i=22605&furl=http://rjwlsy.top/DownloadFile/test.ppt";
                    intent = new Intent(MainActivity.this,WebviewActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    break;
                case  R.id.word_btu:
                    url = "http://ow365.cn/?i=22605&n=3&furl=http://rjwlsy.top/DownloadFile/test.docx";
                    intent = new Intent(MainActivity.this,WebviewActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    break;
                case  R.id.pdf_btu:
                    url = "http://ow365.cn/?i=22605&n=3&furl=http://rjwlsy.top/DownloadFile/test.pdf";
                    intent = new Intent(MainActivity.this,WebviewActivity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                    break;
                case R.id.mupdf_btu:
                    filePath = Environment.getExternalStorageDirectory()+ "/AndroidTestFile/test.pdf";
                    intent = new Intent(MainActivity.this, PdfActivity.class);
                    intent.putExtra("filePath",filePath);
                    startActivity(intent);
                    break;
                case R.id.tbs_word_btu:
                    filePath = Environment.getExternalStorageDirectory()+ "/AndroidTestFile/test.docx";
                    intent = new Intent(MainActivity.this, ReadFileByTBSActivity.class);
                    intent.putExtra("filePath",filePath);
                    startActivity(intent);
                    break;
                case R.id.tbs_ppt_btu:
                    filePath = Environment.getExternalStorageDirectory()+ "/AndroidTestFile/test.ppt";
                    intent = new Intent(MainActivity.this, ReadFileByTBSActivity.class);
                    intent.putExtra("filePath",filePath);
                    startActivity(intent);
                    break;
                case R.id.tbs_pdf_btu:
                    filePath = Environment.getExternalStorageDirectory()+ "/AndroidTestFile/test.pdf";
                    intent = new Intent(MainActivity.this, ReadFileByTBSActivity.class);
                    intent.putExtra("filePath",filePath);
                    startActivity(intent);
                    break;
                case R.id.tbs_excel_btu:
                    filePath = Environment.getExternalStorageDirectory()+ "/AndroidTestFile/test.xlsx";
                    intent = new Intent(MainActivity.this, ReadFileByTBSActivity.class);
                    intent.putExtra("filePath",filePath);
                    startActivity(intent);
                    break;
                default:
                        break;
            }
        }
    }

}
