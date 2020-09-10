package com.digitalchina.webview.activirty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.digitalchina.webview.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;

public class PdfActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener , OnPageErrorListener {

    private static final String TAG = PdfActivity.class.getSimpleName();

    PDFView pdfView;
    Integer pageNumber = 0;
    private String pdfFileName ;
    File file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        pdfView = findViewById(R.id.pdfView);
        Intent intent = getIntent();
        String filePath = intent.getStringExtra("filePath");

        System.out.println("@@@@"+filePath);

        if (filePath!=null){
            file = new File(filePath);
            if (file.exists()){
                int start=filePath.lastIndexOf("/");
                if(start!=-1 ){
                    pdfFileName = filePath.substring(start+1,filePath.length());
                    if (pdfFileName!=null){
                        Log.e("the fileName is: ",pdfFileName);
                    }
                }
                Log.e("file exist!","file exist!");
            }else {
                pdfFileName = null;
                Log.e("file is not exist!","file is not exist!");
                Toast.makeText(this,"File is not exist!",Toast.LENGTH_SHORT);
            }
        }

        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());

        //printBookmarksTree(pdfView.getTableOfContents(), "-");
    }
    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }

}
