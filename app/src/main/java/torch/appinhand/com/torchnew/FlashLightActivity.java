package torch.appinhand.com.torchnew;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;

public class FlashLightActivity extends Activity implements OnClickListener {

    // flag to detect flash is on or off
    String flash = "flash", bright1 = "bright";
    public static boolean isLighOn = false;
    public static boolean isScreenOn = false;

    public static Camera camera;
    String name1;
    private Button btn_Flash;
    ImageView btn_Setting, btn_i, btn_flash1;
    String bright = "bright";
    RelativeLayout rel_Main;
    boolean check = false;

    // AdMob
    AdView adView;
    RelativeLayout adlayout;
    AdRequest request;
    String publisherId = torch.appinhand.com.torchnew.Settings.adMobPulisherId;
    String testingDeviceId = torch.appinhand.com.torchnew.Settings.testingDeviceId;

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (camera != null) {
            camera.release();
            camera = null;
        }

        isLighOn = false;
        isScreenOn = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        // in OnCreate
        adsWork();


//        final SharedPreferences prefsecond = getSharedPreferences("myprefrence",
//                Context.MODE_PRIVATE);
//        String name = "flash";
//        name1 = prefsecond.getString("value1", name);

        name1="flash";
        // Normal
        btn_Flash = (Button) findViewById(R.id.buttonFlashlight);
        btn_Setting = (ImageView) findViewById(R.id.buttonSetting);
        btn_flash1 = (ImageView) findViewById(R.id.flash);


        rel_Main = (RelativeLayout) findViewById(R.id.main_rel);

        btn_flash1.setImageResource(R.drawable.icon_torch_pressed);
        btn_Flash.setOnClickListener(this);
        //btn_Setting.setOnClickListener(this);

        //	btn_flash1.setOnClickListener(this);


        if (name1.equalsIgnoreCase("flash")) {
            if (camera == null) {
                camera = Camera.open();
            }
            if (isLighOn) {
                rel_Main.setBackgroundResource(R.drawable.flash_on);
                btn_Setting.setBackgroundResource(R.drawable.icon_mobile_pressed);


            } else {
                rel_Main.setBackgroundResource(R.drawable.flash_off);
                btn_Setting.setBackgroundResource(R.drawable.icon_mobile);

            }
        }
        if (name1.equalsIgnoreCase("bright")) {
            if (isScreenOn) {
                rel_Main.setBackgroundResource(R.drawable.flash_on);
                btn_Setting.setBackgroundResource(R.drawable.icon_mobile_pressed);

            } else {
                rel_Main.setBackgroundResource(R.drawable.flash_off);
                btn_Setting.setBackgroundResource(R.drawable.icon_mobile);


            }
        }


        //


        Context context = this;
        PackageManager pm = context.getPackageManager();


        SharedPreferences prefs = getSharedPreferences("myprefrence",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor preeditor = prefs.edit();

        btn_Setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                preeditor.putString("value1", bright);
//                preeditor.commit();

name1="bright";


                //btn_flash1.setEnabled(false);
                //name1 = prefsecond.getString("value1","bright");

                btn_Setting.setImageResource(R.drawable.icon_mobile_pressed);

                btn_flash1.setImageResource(R.drawable.icon_torch);

//				if (FlashLightActivity.camera != null) {
//					// FlashLightActivity.camera.release();
//					Log.e("info", "torch is turn off!");
//
//					Parameters p = null;
//					try {
//						p = FlashLightActivity.camera.getParameters();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					p.setFlashMode(Parameters.FLASH_MODE_OFF);
//					FlashLightActivity.camera.setParameters(p);
//					FlashLightActivity.camera.stopPreview();
//				}

                isLighOn = false;

                //finish();
            }
        });


        btn_flash1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                preeditor.putString("value1", flash);
//                preeditor.commit();

                name1="flash";




              //  btn_Setting.setEnabled(false);
                //	name1 = prefsecond.getString("value1","flash");


                btn_flash1.setImageResource(R.drawable.icon_torch_pressed);

                btn_Setting.setImageResource(R.drawable.icon_mobile);





                //		if (FlashLightActivity.camera == null) {
                //			FlashLightActivity.camera = Camera.open();
                //	}

                isScreenOn = false;

                //	finish();
            }
        });

        // if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
        // Log.e("err", "Device has no flash!");
        //
        // SharedPreferences prefs = getSharedPreferences("myprefrence",
        // Context.MODE_PRIVATE);
        // final SharedPreferences.Editor preeditor = prefs.edit();
        //
        // preeditor.putString("value1", bright);
        // preeditor.commit();
        // }

    }

    @Override
    public void onClick(View v) {
        // TODO A+-uto-generated method stub


//        SharedPreferences prefsecond = getSharedPreferences("myprefrence",
//                Context.MODE_PRIVATE);
//
//        name1 = prefsecond.getString("value1", "flash");

        if (v == btn_Flash) {

            try {
                if (name1.equalsIgnoreCase("flash")) {


                    Parameters p = null;
                    try {
                        p = camera.getParameters();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (isLighOn) {
                        btn_Setting.setEnabled(true);
                        rel_Main.setBackgroundResource(R.drawable.flash_off);
                        btn_Setting
                                .setBackgroundResource(R.drawable.icon_mobile_pressed);


                        Log.e("info", "torch is turn off!");

                        p.setFlashMode(Parameters.FLASH_MODE_OFF);
                        camera.setParameters(p);
                        camera.stopPreview();
                        isLighOn = false;

                    } else {

                        Log.e("info", "torch is turn on!");

                        p.setFlashMode(Parameters.FLASH_MODE_TORCH);

                        camera.setParameters(p);
                        camera.startPreview();
                        isLighOn = true;

                        btn_Setting.setEnabled(false);
                        rel_Main.setBackgroundResource(R.drawable.flash_on);
                        btn_Setting.setBackgroundResource(R.drawable.icon_mobile);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (name1.equalsIgnoreCase("bright")) {

                if (isScreenOn) {
                    btn_flash1.setEnabled(true);
                    isScreenOn = false;

                    rel_Main.setBackgroundResource(R.drawable.flash_off);

                    btn_Setting.setBackgroundResource(R.drawable.icon_mobile_pressed);

                } else {
                    btn_flash1.setEnabled(false);
                    rel_Main.setBackgroundResource(R.drawable.flash_on);
                    btn_Setting.setBackgroundResource(R.drawable.icon_mobile);

                    int brightnessMode = 0;
                    try {
                        brightnessMode = Settings.System.getInt(
                                getContentResolver(),
                                Settings.System.SCREEN_BRIGHTNESS_MODE);
                    } catch (SettingNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (brightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                        Settings.System.putInt(getContentResolver(),
                                Settings.System.SCREEN_BRIGHTNESS_MODE,
                                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                    }

                    WindowManager.LayoutParams layoutParams = getWindow()
                            .getAttributes();
                    layoutParams.screenBrightness = 1.0F; // set 50% brightness
                    getWindow().setAttributes(layoutParams);

                    isScreenOn = true;

                }
            }

        }


    }

//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		SharedPreferences prefsecond = getSharedPreferences("myprefrence",
//				Context.MODE_PRIVATE);
//		String name = "flash";
//		name1 = prefsecond.getString("value1", name);
//
//		if (name1.equals("flash")) {
//			if (camera == null) {
//				camera = Camera.open();
//			}
//			if (isLighOn) {
//				rel_Main.setBackgroundResource(R.drawable.flash_on);
//				btn_Setting.setBackgroundResource(R.drawable.icon_torch);
//
//			} else {
//				rel_Main.setBackgroundResource(R.drawable.flash_off);
//				btn_Setting.setBackgroundResource(R.drawable.icon_torch_pressed);
//
//			}
//		}
//		if (name1.equals("bright")) {
//			if (isScreenOn) {
//				rel_Main.setBackgroundResource(R.drawable.flash_on);
//				btn_Setting.setBackgroundResource(R.drawable.icon_mobile);
//
//			} else {
//				rel_Main.setBackgroundResource(R.drawable.flash_off);
//				btn_Setting.setBackgroundResource(R.drawable.icon_mobile_pressed);
//
//
//			}
//		}
//	}// /// Function

    void adsWork() {
        // Admob
        //AdView adView;
        RelativeLayout adlayout;
        //AdRequest request;
        String publisherId = com.appinhand.TorchEnglishFree.Settings.adMobPulisherId;
        String testingDeviceId = com.appinhand.TorchEnglishFree.Settings.testingDeviceId;
        try {
            adlayout = (RelativeLayout) findViewById(R.id.adLayout);
            // Get Screen
            Display display = ((WindowManager) this
                    .getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay();
            int screenWidth = display.getWidth();
            Log.e("Screen Width", "" + screenWidth);

            // AdMob Size Initialisations
            if (screenWidth > 300 && screenWidth <= 320)
                adView = new AdView(this, AdSize.BANNER, publisherId);
            else
                adView = new AdView(this, AdSize.SMART_BANNER, publisherId);

            adlayout.addView(adView);

            // AdMob Request
            request = new AdRequest();

            // only for testing Devices
            request.addTestDevice(AdRequest.TEST_EMULATOR);
            request.addTestDevice(testingDeviceId);
            adView.loadAd(request);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
