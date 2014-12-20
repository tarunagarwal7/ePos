package co.zakuna.pos;

import org.androidannotations.annotations.*;
import android.widget.*;
import android.view.View;
import android.view.LayoutInflater;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import co.zakuna.pos.adapter.SmartFragmentStatePagerAdapter;

@EFragment(R.layout.fragment_tab)
public class SearchDialogFragment extends BaseDialogFragment implements
	ViewPager.OnPageChangeListener, 
	TabHost.OnTabChangeListener {
	
	@ViewById FragmentTabHost tabhost;
	@ViewById ViewPager viewpager;
	
	private OnCallback callback;

	@AfterViews
	protected void initialized() {
		tabhost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
		SearchAdapter adapter = new SearchAdapter(getChildFragmentManager());
		viewpager.setAdapter(adapter);
		viewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
			@Override public void transformPage(View page, float position) {
				page.setRotationY(position * -30);
			}
		});

		viewpager.setOnPageChangeListener(this);
		tabhost.setOnTabChangedListener(this);
		for(int i=0; i<7; i++) setTabhost("Menu "+(i+1));
	}

	private void setTabhost(String title) {
		tabhost.addTab(tabhost.newTabSpec(title).setIndicator(getIndicator(title)),
				SearchContentDialogFragment.class, null);
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

	private class SearchAdapter extends SmartFragmentStatePagerAdapter {
		public SearchAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override 
		public int getCount() { 
			return 7; 
		}
		
		@Override 
		public Fragment getItem(int position) {
			SearchContentDialogFragment fragment = SearchContentDialogFragment_
				.builder().id(position).build(); 
			fragment.callback(new OnCallback() {
				@Override
				public void setOnCallback(Object object) {
					SearchDialogFragment.this.dismiss();
					SearchDialogFragment.this.callback.setOnCallback(object);
				}
			});
			return fragment;
		}
	}

}
