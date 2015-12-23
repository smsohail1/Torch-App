package torch.appinhand.com.torchnew;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.appinhand.torch.R;

public class SettingScreen extends Activity {

	Intent myIntent;
	ImageView flashlight, brightness, btn_about;
	String flash = "flash", bright = "bright";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		SharedPreferences prefs = getSharedPreferences("myprefrence",
				Context.MODE_PRIVATE);
		final SharedPreferences.Editor preeditor = prefs.edit();

		flashlight = (ImageView) findViewById(R.id.flashlight);
		brightness = (ImageView) findViewById(R.id.brightness);

		flashlight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				// TODO Auto-generated method stub

				preeditor.putString("value1", flash);
				preeditor.commit();

				if (FlashLightActivity.camera == null) {
					FlashLightActivity.camera = Camera.open();
				}

				FlashLightActivity.isScreenOn = false;

				finish();

			}
		});

		brightness.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				// TODO Auto-generated method stub
				preeditor.putString("value1", bright);
				preeditor.commit();

				if (FlashLightActivity.camera != null) {
					// FlashLightActivity.camera.release();
					Log.e("info", "torch is turn off!");

					Parameters p = null;
					try {
						p = FlashLightActivity.camera.getParameters();
					} catch (Exception e) {
						e.printStackTrace();
					}
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					FlashLightActivity.camera.setParameters(p);
					FlashLightActivity.camera.stopPreview();
				}

				FlashLightActivity.isLighOn = false;

				finish();
			}
		});

	}

	public static boolean hasCameraFlash(Camera camera) {
		Parameters p = camera.getParameters();
		return p.getFlashMode() == null ? false : true;
	}
}
