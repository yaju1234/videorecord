package com.example.android.camera2video;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class ShowAllView extends Activity {
    public static final String APP_DIR = "VideoRecodingCompressor";

    public static final String COMPRESSED_VIDEOS_DIR = "/Compressed Videos/";

    public static final String TEMP_DIR = "/Temp/";
    ArrayAdapter adapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_view);
        System.out.println("!!!!FileName:");
        listView = (ListView) findViewById(R.id.mobile_list);
        stringArrayList.clear();
        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, stringArrayList);
        listView.setAdapter(adapter);
        String path = getExternalStorageDirectory().toString() + File.separator
                + APP_DIR
                + COMPRESSED_VIDEOS_DIR;
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        for (int i = files.length-1; i >=0; i--) {
            System.out.println("!!!!FileName:" + files[i].getName());
            stringArrayList.add(files[i].getName());

        }

        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("!!!!"+stringArrayList.get(i));
                File directory = new File(getVideoFilePath(stringArrayList.get(i)));
                System.out.println("!!!!"+directory);
                Uri photoURI = Uri.fromFile(directory);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(photoURI)));
                intent.setDataAndType(Uri.parse(String.valueOf(photoURI)), "video/mp4");
                startActivity(intent);


            }
        });
    }
    private String getVideoFilePath( String movieurl) {

        return (getExternalStorageDirectory().getAbsolutePath()  + File.separator
                + APP_DIR
                + COMPRESSED_VIDEOS_DIR)
                + movieurl;
    }
}
