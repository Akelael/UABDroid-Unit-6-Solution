/*
   Copyright 2012 Ruben Serrano

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.uab.deic.uabdroid.solutions.unit6;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyGeocodedMapActivity extends MapActivity 
{
	@Override
	protected void onCreate(Bundle _savedInstanceState) 
	{
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.map_layout);
		
		MapView mapView = (MapView)findViewById(R.id.map_view);
		mapView.setSatellite(true);
		
		GeoPoint point = new GeoPoint((int)(41.499876*1E6),(int)(2.11275*1E6));
		
		MapController mapController = mapView.getController();
		mapController.setCenter(point);
		mapController.setZoom(16);
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.map_icon);
		MyOverlays itemizedoverlays = new MyOverlays(drawable, this);

		OverlayItem overlayitem = new OverlayItem(point, "Q5/2008", "UABDroid");

		itemizedoverlays.addOverlay(overlayitem);
		
		Geocoder geocoder = new Geocoder(getApplicationContext(), new Locale("ca_ES"));
		List<Address> addressList;
		try 
		{
			addressList = geocoder.getFromLocationName("Plaça Cívica, Bellaterra 08193", 1);
			Address address = addressList.get(0);
			point = new GeoPoint((int)(address.getLatitude()*1E6), (int)(address.getLongitude()*1E6));
			itemizedoverlays.addOverlay(new OverlayItem(point, "Plaça Cívica", "Universitat Autònoma de Barcelona"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		mapOverlays.add(itemizedoverlays);
	}

	@Override
	protected boolean isRouteDisplayed() 
	{
		return false;
	}
}
