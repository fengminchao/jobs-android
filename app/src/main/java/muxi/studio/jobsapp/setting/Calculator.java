//package muxi.studio.jobsapp.setting;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import muxi.studio.jobsapp.R;
//
///**
// * Created by ybao on 16/7/16.
// */
//public class Calculator {
//    @Bind(R.id.btn_camera)
//    Button mBtnCamera;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//        ButterKnife.bind(this);
//        mBtnCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    requestCameraPermission();
//                }
//            }
//        });
//
//    }
//
//    private static final int REQUEST_PERMISSION_CAMERA_CODE = 1;
//
//    private void requestCameraPermission() {
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
//            requestPermissions(new String[]{
//                    Manifest.permission.CAMERA
//            }, REQUEST_PERMISSION_CAMERA_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSION_CAMERA_CODE) {
//            int grantResult = grantResults[0];
//            boolean granted = grantResult == PackageManager.PERMISSION_GRANTED;
//            Log.i("logtag", "onRequestPermissionsResult granted=" + granted);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//}
