package com.carouseldemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;


public class Utilities {

	public static String JOCmd5(String s) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Bitmap ResizeImage(Drawable img, int maxWidth, int maxHeight,Context ctx){
		maxWidth=Utilities.pixelTodpi(ctx, maxWidth);
		maxHeight=Utilities.pixelTodpi(ctx, maxHeight);
		
	    double srcWidth = img.getIntrinsicWidth();
	    double srcHeight = img.getIntrinsicHeight();

	    double resizeWidth = srcWidth;
	    double resizeHeight = srcHeight;

	    double aspect = resizeWidth / resizeHeight;

	    if (resizeWidth != maxWidth) {
	        resizeWidth = maxWidth;
	        resizeHeight = resizeWidth / aspect;
	    }
	    if (resizeHeight != maxHeight) {
	        aspect = resizeWidth / resizeHeight;
	        resizeHeight = maxHeight;
	        resizeWidth = resizeHeight * aspect;
	    }	    
	    Bitmap bit=((BitmapDrawable)img).getBitmap();
		bit=Bitmap.createScaledBitmap(bit, (int)resizeWidth, (int)resizeHeight, false);		
		return bit;

	}
	
	public static Bitmap ResizeImage(Drawable img, int maxWidth, int maxHeight,boolean toDpi ,Context ctx) {
		if(toDpi){
		maxWidth=Utilities.pixelTodpi(ctx, maxWidth);
		maxHeight=Utilities.pixelTodpi(ctx, maxHeight);
		}
	    double srcWidth = img.getIntrinsicWidth();
	    double srcHeight = img.getIntrinsicHeight();

	    double resizeWidth = srcWidth;
	    double resizeHeight = srcHeight;

	    double aspect = resizeWidth / resizeHeight;

	    if (resizeWidth != maxWidth) {
	        resizeWidth = maxWidth;
	        resizeHeight = resizeWidth / aspect;
	    }
	    if (resizeHeight != maxHeight) {
	        aspect = resizeWidth / resizeHeight;
	        resizeHeight = maxHeight;
	        resizeWidth = resizeHeight * aspect;
	    }
	    
	    Bitmap bit=((BitmapDrawable)img).getBitmap();
		bit=Bitmap.createScaledBitmap(bit, (int)resizeWidth, (int)resizeHeight, false);		
		return bit;

	}
	
	public static Bitmap ResizeImage(Bitmap img, int maxWidth, int maxHeight,boolean toDpi,Context ctx) {
		if(toDpi){
			maxWidth=Utilities.pixelTodpi(ctx, maxWidth);
			maxHeight=Utilities.pixelTodpi(ctx, maxHeight);
		}
	    double srcWidth = img.getWidth();
	    double srcHeight = img.getHeight();
	    double resizeWidth = srcWidth;
	    double resizeHeight = srcHeight;
	    double aspect = resizeWidth / resizeHeight;
	    if (resizeWidth != maxWidth){
	        resizeWidth = maxWidth;
	        resizeHeight = resizeWidth / aspect;
	    }
	    if (resizeHeight != maxHeight){
	        aspect = resizeWidth / resizeHeight;
	        resizeHeight = maxHeight;
	        resizeWidth = resizeHeight * aspect;
	    }
	    
	    Bitmap bit=Bitmap.createScaledBitmap(img, (int)resizeWidth, (int)resizeHeight, false);
		
		return bit;

	}
	
	public static int pixelTodpi(Context ctx,float dp){
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();		
		int pixels = (int) (metrics.density * dp + 0.5f);
		return pixels;
	}
	
	public static float floatTodpi(Context ctx,float dp){
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
		float fpixels = metrics.density * dp;
		return fpixels;
	}
	
}
