package com.jaycejia;

import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jaycejia.common.AppCompatAndroidApplication;
import com.jaycejia.databinding.ActivityMainBinding;
import com.jaycejia.databinding.FragmentMyRefrigeratorBinding;
import com.jaycejia.fragment.RefrigeratorListFragment;
import com.jaycejia.utils.LogUtil;
import com.jaycejia.utils.StatusBarUtil;
import com.jaycejia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatAndroidApplication implements View.OnClickListener, AdapterView.OnItemSelectedListener {
	private ActivityMainBinding binding = null;
	private int[] bgSelector = {R.drawable.tab1_picture_selector, R.drawable.tab2_picture_selector};
	private List<Fragment> mFragments = new ArrayList<>();
	private MainViewPagerAdapter adapter = null;
	private View view = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StatusBarUtil.fitSystemBarTextColor(this);
		this.binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false);
		setContentView(this.binding.getRoot());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.r = config.g = config.b = config.a = 8;
		this.view = initializeForView(new MyGdxGame(), config);
		if (graphics.getView() instanceof SurfaceView) {
			SurfaceView glView = (SurfaceView) graphics.getView();
			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
			glView.setZOrderOnTop(true);
		}

		this.binding.llPersonal.setOnClickListener(this);
		this.binding.llShare.setOnClickListener(this);
		this.binding.llMyFamily.setOnClickListener(this);
		this.binding.llAbout.setOnClickListener(this);

		initSpinner();
		initFragment();
		initTabLayout();
	}

	private void initFragment() {
		this.mFragments.add(new MyRefrigeratorFragment());
		this.mFragments.add(new RefrigeratorListFragment());
		this.adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this.mFragments);
		this.binding.viewPager.setAdapter(this.adapter);
		this.binding.viewPager.setCurrentItem(0);
		this.binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
		this.binding.tabLayout.setupWithViewPager(this.binding.viewPager);
	}

	private void initSpinner() {
		String[] items = getResources().getStringArray(R.array.personal_selector);
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.binding.spinner.setAdapter(adapter);
		this.binding.spinner.setOnItemSelectedListener(this);

		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		this.binding.spinner.measure(w, h);
		int height = this.binding.spinner.getMeasuredHeight();
		this.binding.spinner.setDropDownVerticalOffset(height);
	}

	private void initTabLayout() {
//        for (int i =0;i<2;i++) {
//            TabLayout.Tab tab = this.binding.tabLayout.newTab();
//            TabViewBinding tabViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.tab_view, null, false);
//            tabViewBinding.txtTab.setBackgroundResource(bgSelector[i]);
//            tab.setCustomView(tabViewBinding.getRoot());
//            if (i == 0) {
//                tabViewBinding.txtTab.setFocusable(true);
//            }
//            this.binding.tabLayout.addTab(tab);
//        }
		for (int i =0;i<bgSelector.length;i++) {
			this.binding.tabLayout.getTabAt(i).setIcon(ContextCompat.getDrawable(this, bgSelector[i]));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_personal:
				ToastUtil.showToast("功能暂未开发");
				break;
			case R.id.ll_my_family:
				ToastUtil.showToast("功能暂未开发");
				break;
			case R.id.ll_share:
				ToastUtil.showToast("功能暂未开发");
				break;
			case R.id.ll_about:
				ToastUtil.showToast("功能暂未开发");
				break;
			default:break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String[] items = getResources().getStringArray(R.array.personal_selector);
		ToastUtil.showToast(items[position]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	private static class MainViewPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments = new ArrayList<>();

		public MainViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
			super(fragmentManager);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "";
		}
	}

	public static class MyRefrigeratorFragment extends Fragment {
		private FragmentMyRefrigeratorBinding binding = null;
		@Override
		public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			this.binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_my_refrigerator, container, false);
			this.binding.freezerContainer.addView(((MainActivity) getContext()).view);
			return this.binding.getRoot();
		}
	}
}
