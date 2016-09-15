package com.andromed.codes.universalresourcedownloader.demo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.andromed.codes.universalresourcedownloader.R;
import com.andromed.codes.universalresourcedownloader.demo.adapters.ListViewAdapter;
import com.andromed.codes.universalresourcehandler.callbacks.CallbackInterface;
import com.andromed.codes.universalresourcehandler.parsers.JsonParser;
import com.andromed.codes.universalresourcehandler.imageHandler.ImageLoader;

import java.util.ArrayList;

import com.andromed.codes.universalresourcedownloader.demo.scheme.Urls;

public class ImageListActivity extends AppCompatActivity {

    private ImageLoader mImageLoader;
    private ListView listView;
    Button btGo;
    JsonParser jsonParser;
    ArrayList<Urls> jsonResponse2;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        btGo = (Button) findViewById(R.id.go);
        listView = (ListView) findViewById(R.id.imageList);
        mImageLoader = new ImageLoader();
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageListActivity.this, JsonParseExampleActivity.class));
                ImageListActivity.this.finish();
            }
        });
        jsonParser = new JsonParser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ArrayList<Bitmap> array = new ArrayList<>();
        adapter = new ListViewAdapter(ImageListActivity.this, array);
        listView.setAdapter(adapter);
        jsonParser.prepare(new Runnable() {
            @Override
            public void run() {
                jsonParser.getDataFromJson("http://pastebin.com/raw/wgkJgazE", Urls.class,
                        new CallbackInterface() {
                            @Override
                            @SuppressWarnings("unchecked")
                            public void onSuccess(Object result) {
                                jsonResponse2 = ((ArrayList<Urls>) result);
                                final ArrayList<String> urlss = new ArrayList<>();
                                int c = 0;
                                for (Urls urls :
                                        jsonResponse2) {
                                    urlss.add(urls.getFull());
                                    c++;
                                    if (c==5)
                                        break;
                                }
                                mImageLoader.loadImageBundle(ImageListActivity.this, urlss, 100, 100, new CallbackInterface() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        Bitmap bitmap = (Bitmap) result;
                                        array.add(bitmap);
                                        adapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onFailure() {
                                        Toast.makeText(ImageListActivity.this, "Image not loaded", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                if (jsonResponse2 != null) {
                                    Log.e("Response", jsonResponse2.get(0)
                                            .getRaw());
                                }
                            }

                            @Override
                            public void onFailure() {
                                //Do nothing
                            }
                        });
            }
        });
    }
}
