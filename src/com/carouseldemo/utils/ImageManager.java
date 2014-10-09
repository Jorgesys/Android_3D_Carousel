package com.carouseldemo.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.carouseldemo.main.R;

public class ImageManager{

	private static String TAG = "ImageManager";
	private OnImageLoadedListener onImageLoadedListener=null;

	public ImageManager(){


	}

	public void startDownload(int position,String url,Context ctx){
		new ImageBitmapLazyDownload(position,url,ctx).execute();
	}

	public void setOnImageLoadedListener(OnImageLoadedListener listener) {
		onImageLoadedListener = listener;
	}

	// This function is called after the check was complete
	private void OnImageDownloaded(int position,Bitmap bit,String url){
		// Check if the Listener was set, otherwise we'll get an Exception when we try to call it
		if(onImageLoadedListener!=null) {
			// Only trigger the event, when we have a username

			onImageLoadedListener.onImageLoaded(position,bit,url);

		}
	}

	protected class ImageBitmapLazyDownload extends
	AsyncTask<ImageView, ImageView, Long> {
		Bitmap drawable;
		String url;
		Context mContext;
		int position = 0;


		public ImageBitmapLazyDownload(int pos,String url,Context context) {
			position = pos;
			mContext =context;
			this.url=url;
		}



		@Override
		protected Long doInBackground(ImageView... params) {
			try {
				final String fotoname = JOCmd5(url)+".jpg";		
				if (url == null) {
					drawable = getPlaceholder(); // get a default drawable
				} else {
					drawable = ImageOperations(mContext, url, fotoname); 
					publishProgress();
				}
			} catch (Exception ex) {
				Log.i(TAG, ex.getMessage());
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(ImageView... params) {				
			if (drawable != null) {
				OnImageDownloaded(position,drawable,url);
			}			
		}

		protected void onPostExecute(Long result) {
			Log.i(TAG, "Downloaded " + result+ " bytes");
		}

		public Bitmap getPlaceholder() {
			return drawableToBitmap(mContext.getResources().getDrawable(
					R.drawable.nothumb));
		}

		public Bitmap drawableToBitmap(Drawable drawable) {

			Bitmap bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
									: Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);

			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			drawable.draw(canvas);
			return bitmap;
		}

		private Bitmap ImageOperations(Context ctx, String url,
				String saveFilename) {
			try {
				String filepath = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/grTemp/";
				File cacheFile = new File(filepath + saveFilename);
				cacheFile.deleteOnExit();
				cacheFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(cacheFile);
				InputStream is = (InputStream) this.fetch(url);

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 8;

				Bitmap bitmap = BitmapFactory.decodeStream(is);
				bitmap.compress(CompressFormat.PNG, 60, fos);
				fos.flush();
				fos.close();
				return bitmap;

			} catch (MalformedURLException e) {
				Log.v(TAG, e.getMessage());
				return null;
			} catch (IOException e) {
				Log.v(TAG, e.getMessage());
				return null;
			}
		}

		public Object fetch(String address) throws MalformedURLException,
		IOException {
			URL url = new URL(address);
			Object content = url.getContent();
			return content;
		}

		public String JOCmd5(String s) {
			try {
				MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
				digest.update(s.getBytes());
				byte messageDigest[] = digest.digest();
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < messageDigest.length; i++)
					hexString.append(Integer
							.toHexString(0xFF & messageDigest[i]));
				return hexString.toString();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return "";
		}

	}

}


