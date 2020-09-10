package com.digitalchina.webview;

import java.util.List;

public class FileTypeMoudel {

    private boolean isExpand = false;

    private int fileItemNums;

    private List<FileMoudel> fileMoudelList;


    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getFileItemNums() {
        return fileItemNums;
    }

    public void setFileItemNums(int fileItemNums) {
        this.fileItemNums = fileItemNums;
    }

    public List<FileMoudel> getFileMoudelList() {
        return fileMoudelList;
    }

    public void setFileMoudelList(List<FileMoudel> fileMoudelList) {
        this.fileMoudelList = fileMoudelList;
    }

}
