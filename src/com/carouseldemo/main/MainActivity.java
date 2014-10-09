package com.carouseldemo.main;


/***
 * @copyright Tuna puisor
 * Carrousel Demo based on Igor Kushnarev, Android 3D Carousel.
 * created by Jorgesys tuna.puisor@gmail.com 
 */


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenu.SateliteClickedListener;
import android.view.ext.SatelliteMenuItem;
import android.widget.Toast;

import com.carouseldemo.controls.Carousel;
import com.carouseldemo.controls.CarouselAdapter;
import com.carouseldemo.controls.CarouselAdapter.OnItemClickListener;

public class MainActivity extends Activity {

	private static String TAG = "MainActivity";
	protected boolean errorDescarga=false;
	protected boolean seccionLibre=false;	
	public int Adpos = 3;
	public int Adposxml;
	ArrayList<String> reparto;
	ArrayList<String> titulo;
	ArrayList<String> sinopsis;
	ArrayList<String> imagen;
	ArrayList<String> imagengde;
	ArrayList<String> url;
	ArrayList<String> idOpi;
	ArrayList<String> categoria;
	ArrayList<String> ImageLoaded;
	ArrayList<String> idcat;
	ArrayList<String> nombre;
	ArrayList<String> urlFeed;
	ArrayList<String> idopinion;
	ArrayList<String> grcidsubc;
	ArrayList<String> ArticleType;
	protected ArrayList<String> ArticuloAbierto;
	static String Ad_EIEspSecc;
	static String Ad_EIEspSeccClick;
	ArrayList<String> thumbUrlGde;
	ArrayList<String> thumbUrl;
	ArrayList<String> num;
	String Titles = "";
	List<View> views = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Carousel carousel = (Carousel)findViewById(R.id.carousel);
		
		carousel.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {				
				Toast.makeText(MainActivity.this, "Movie No." + position, Toast.LENGTH_SHORT).show();
			}
		});

        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);       
        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(4, R.drawable.ic_1));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_3));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_4));
        items.add(new SatelliteMenuItem(3, R.drawable.ic_5));
        items.add(new SatelliteMenuItem(2, R.drawable.ic_6));
        items.add(new SatelliteMenuItem(1, R.drawable.ic_2));
        menu.addItems(items);               
        menu.setOnItemClickedListener(new SateliteClickedListener() {			
			public void eventOccured(int id) {
				Log.i(TAG, "Clicked on " + id);
			}
		});		
	}


}

