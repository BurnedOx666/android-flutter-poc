package com.example.addflutter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class AppFlutterActivity extends FlutterActivity {

    public static Intent createIntent(Context context) {
        return new NewEngineIntentBuilder(AppFlutterActivity.class).build(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        startMethodChannel(flutterEngine);
    }

    private void startMethodChannel(@NonNull FlutterEngine flutterEngine) {
        new MethodChannel(flutterEngine.getDartExecutor(), "oneplay").setMethodCallHandler(
                (call, result) -> {
                    switch (call.method) {
                        case "openSecondActivity":
                            Intent intent = new Intent(AppFlutterActivity.this, GameActivity.class);
                            startActivity(intent);
                            break;
                        default:
                            return;
                    }
                }
        );
    }
}