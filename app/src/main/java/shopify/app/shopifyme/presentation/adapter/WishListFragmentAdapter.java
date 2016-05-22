package shopify.app.shopifyme.presentation.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import shopify.app.shopifyme.R;
import shopify.app.shopifyme.presentation.view.fragment.OffersFragment;
import shopify.app.shopifyme.presentation.view.fragment.WishesFragment;

public class WishListFragmentAdapter extends FragmentStatePagerAdapter {

    private final List<String> mFragmentTitles = new ArrayList<>();

    public WishListFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);

        mFragmentTitles.add(context.getString(R.string.wishlist));
        mFragmentTitles.add(context.getString(R.string.offers));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new WishesFragment();
                break;
            case 1:
                fragment = new OffersFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}