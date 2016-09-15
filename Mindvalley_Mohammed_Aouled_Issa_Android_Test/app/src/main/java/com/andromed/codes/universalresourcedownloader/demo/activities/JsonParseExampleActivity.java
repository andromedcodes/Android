package com.andromed.codes.universalresourcedownloader.demo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andromed.codes.universalresourcedownloader.R;
import com.andromed.codes.universalresourcehandler.callbacks.CallbackInterface;
import com.andromed.codes.universalresourcehandler.parsers.JsonParser;

import java.util.ArrayList;

import com.andromed.codes.universalresourcedownloader.demo.scheme.RootObject;
import com.andromed.codes.universalresourcedownloader.demo.scheme.Urls;

public class JsonParseExampleActivity extends AppCompatActivity{

    Button back;
    JsonParser jsonParser;
    ArrayList<RootObject> jsonResponse;
    ArrayList<Urls> jsonResponse2;
    private TextView jsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parser_examplet);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JsonParseExampleActivity.this, ImageListActivity.class));
                JsonParseExampleActivity.this.finish();
            }
        });
        jsonParser = new JsonParser();
        jsonResult = (TextView) findViewById(R.id.jsonResult);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jsonParser.prepare(new Runnable() {
            @Override
            public void run() {
                jsonParser.getFullDataFromJson("http://pastebin.com/raw/wgkJgazE", RootObject.class,
                        new CallbackInterface() {
                            @Override
                            @SuppressWarnings("unchecked")
                            public void onSuccess(Object result) {
                                jsonResponse = ((ArrayList<RootObject>) result);
                                if (jsonResponse != null) {
                                    (JsonParseExampleActivity.this).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String listString = "";

                                            for (RootObject s : jsonResponse)
                                            {
                                                listString += s.getUser().getName().toString() + "\t\n";
                                            }
                                            jsonResult.setText(listString);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure() {
                                //Do something
                            }
                        });
            }
        });

        jsonParser.prepare(new Runnable() {
            @Override
            public void run() {
                jsonParser.getDataFromJson("http://pastebin.com/raw/wgkJgazE", Urls.class,
                        new CallbackInterface() {
                            @Override
                            @SuppressWarnings("unchecked")
                            public void onSuccess(Object result) {
                                jsonResponse2 = ((ArrayList<Urls>) result);
                                if (jsonResponse2 != null) {
                                    Log.e("Response", jsonResponse2.get(0)
                                            .getRaw());
                                }
                            }

                            @Override
                            public void onFailure() {
                                //Do something
                            }
                        });
            }
        });
    }
}
