package shopify.app.shopifyme.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.presentation.adapter.WishListFragmentAdapter;

public class WishListFragment extends Fragment {

    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    private WishListFragmentAdapter mWishListAdapter;

    public WishListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        ButterKnife.bind(this, view);

        setUpViewPager();
        return view;
    }


    private void setUpViewPager() {
        mWishListAdapter = new WishListFragmentAdapter(getFragmentManager(), getContext());

        mTabLayout.setTabTextColors(getResources().getColor(android.R.color.white),
                getResources().getColor(android.R.color.white));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue));

        mPager.setAdapter(mWishListAdapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mPager);
    }
}
