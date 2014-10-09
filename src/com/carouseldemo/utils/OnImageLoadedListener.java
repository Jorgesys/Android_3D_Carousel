package com.carouseldemo.utils;

import android.graphics.Bitmap;

		public interface OnImageLoadedListener {
			public abstract void onImageLoaded(int pos,Bitmap bit,String url);
		}