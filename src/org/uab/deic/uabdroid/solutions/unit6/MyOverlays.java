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

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> 
{
	private Context mContext;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	public MyOverlays(Drawable _defaultMarker, Context _context) 
	{
		super(boundCenterBottom(_defaultMarker));
		mContext = _context;
	}
	
	public void addOverlay(OverlayItem _item)
	{
		mOverlays.add(_item);
		populate();
	}

	@Override
	protected OverlayItem createItem(int _index) 
	{
		return mOverlays.get(_index);
	}
	
	@Override
	public boolean onTap(int _index) 
	{
		OverlayItem item = mOverlays.get(_index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

	@Override
	public int size() 
	{
		return mOverlays.size();
	}
}
