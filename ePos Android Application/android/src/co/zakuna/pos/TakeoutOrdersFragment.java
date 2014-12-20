package co.zakuna.pos;

import lombok.*;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.*;

import android.widget.*;
import android.view.View;
import android.view.LayoutInflater;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;
import java.util.ArrayList;

import co.zakuna.pos.adapter.SmartFragmentStatePagerAdapter;

@EFragment(R.layout.fragment_takeout_tab)
public class TakeoutOrdersFragment extends Fragment implements
	ViewPager.OnPageChangeListener, 
	TabHost.OnTabChangeListener {
	
	@ViewById FragmentTabHost tabhost;
	@ViewById ViewPager viewpager;

	@StringRes(R.string.title_takeout_ordering) String orderingText;
	@StringRes(R.string.title_takeout_ordered) String orderedText;

	@FragmentArg int id;
	@FragmentArg Integer guest;

	public final static int KEY_LEFT = 0;
	public final static int KEY_RIGHT = 1;

	private OrderAdapter adapter;
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		tabhost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
		viewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
			@Override public void transformPage(View page, float position) {
				page.setRotationY(position * -30);
			}
		});

		viewpager.setOnPageChangeListener(this);
		tabhost.setOnTabChangedListener(this);
		initFragment();
	}

	protected void initFragment() {
		if(id == KEY_LEFT) {
			adapter = new OrderAdapter(getChildFragmentManager(), 2);
			viewpager.setAdapter(adapter);
			setTabhostOrdering();
			setTabhostOrdered();
		} else {
			adapter = new OrderAdapter(getChildFragmentManager(), 7);
			viewpager.setAdapter(adapter);
			for(int i=0; i<7; i++) setTabhostMenuItem("Menu "+(i+1));
		}
	}

	private void setTabhostMenuItem(String title) {
		tabhost.addTab(tabhost.newTabSpec(title)
				.setIndicator(getIndicator(title)), TakeoutProductsFragment.class, null);
	}

	private void setTabhostOrdering() {
		tabhost.addTab(tabhost.newTabSpec(orderingText)
				.setIndicator(getIndicator(orderingText)), TakeoutOrderingFragment.class, null);
	}

	private void setTabhostOrdered() {
		tabhost.addTab(tabhost.newTabSpec(orderedText)
				.setIndicator(getIndicator(orderedText)), TakeoutOrderedFragment.class, null);
	}

	private View getIndicator(String title) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_indicator, null);
		TextView mTitle = (TextView) view.findViewById(R.id.title);
		mTitle.setText(title);
		return view;
	}

	@Override
	public void onTabChanged(String tabId) {
		int pos = tabhost.getCurrentTab();
		viewpager.setCurrentItem(pos);
	}

	@Override
	public void onPageSelected(int pos) {
		tabhost.setCurrentTab(pos);
	}

	@Override public void onPageScrolled(int arg0, float arg1, int arg2) {}
	@Override public void onPageScrollStateChanged(int arg0) {}

	public void callback(OnCallback callback) {
		this.callback = callback;
	}

	private class OrderAdapter extends SmartFragmentStatePagerAdapter {
		private int count;
		public OrderAdapter(FragmentManager fm, int count) {
			super(fm);
			this.count = count;
		}

		@Override 
		public int getCount() { 
			return count; 
		}

		@Override 
		public Fragment getItem(int position) {
			if(count == 2) {
				switch(position) {
					case 0: 
						TakeoutOrderingFragment fOrdering = TakeoutOrderingFragment_
							.builder().guest(guest).build();
						fOrdering.callback(new OnCallback(){
							@Override public void setOnCallback(Object object) {
								TakeoutOrdersFragment.this.viewpager.setCurrentItem(1, true);
								TakeoutOrdersFragment.this.adapter.notifyDataSetChanged();	
							}
						});
						return fOrdering;
					case 1: 
						TakeoutOrderedFragment fOrdered = TakeoutOrderedFragment_
							.builder().guest(guest).build();
						return fOrdered;
				}
			} else {
				TakeoutProductsFragment fragment = TakeoutProductsFragment_
					.builder().id(position).build();
				fragment.callback(new OnCallback() {
					@Override public void setOnCallback(Object object) {
						TakeoutOrdersFragment.this.callback.setOnCallback(object);
					}
				});
				return fragment;
			}
			return null;
		}
	}
}
