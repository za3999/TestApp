package com.test.util;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.test.MusicApplication;

import java.io.File;

public class DownloadUtil {

    public static boolean isMusicExist(String title, String artist) {
        return new File(getDownloadBaseFile(), getFileName(title, artist)).exists();
    }

    public static String getMusicPath(String title, String artist) {
        return new File(getDownloadBaseFile(), getFileName(title, artist)).getPath();
    }

    public static String getFileName(String title, String artist) {
        return title + "_" + artist + ".mp3";
    }

    public static String getFileName(String title, String artist,String bitrate) {
        return title + "_" + artist + "_" + bitrate +".mp3";
    }

    public static long download(String url, String fileName) {
        long downloadId = -1;
        if (url != null) {
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setMimeType(getMimeTYpe(url));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            request.setVisibleInDownloadsUi(false);
            Uri fileUri = Uri.withAppendedPath(Uri.fromFile(getDownloadBaseFile()), fileName);
            request.setDestinationUri(fileUri);
            request.setTitle(fileName);
            DownloadManager downloadManager = (DownloadManager) MusicApplication.getApplication().getSystemService(Context.DOWNLOAD_SERVICE);
            downloadId = downloadManager.enqueue(request);
        }
        return downloadId;
    }

    private static String getMimeTYpe(String url) {
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String type = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        LogUtils.d("MimeTYpe:" + type);
        return type;
    }

    private static File getDownloadBaseFile() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "music/");
    }

}
