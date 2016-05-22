package shopify.app.shopifyme.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.data.repository.WishRepository;
import shopify.app.shopifyme.domain.entities.WishList;
import shopify.app.shopifyme.presentation.adapter.WishesAdapter;
import shopify.app.shopifyme.presentation.application.AndroidApplication;
import shopify.app.shopifyme.presentation.dialog.AddWishDialog;

/**
 * Created by caueferreira on 5/22/16.
 */
public class WishesFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.wish_list_hint)
    TextView mTxtWishListHint;
    @Bind(R.id.wish_list)
    RecyclerView mRclWishList;
    private RecyclerView.LayoutManager mLayoutManager;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private WishRepository mWishRepository;
    private Subscriber<WishList> mWishListSubscriber;

    private WishesAdapter mWishesAdapter;

    private WishList mWishList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRclWishList.setLayoutManager(mLayoutManager);

        initRepositories();
        initSubscribers();

        mWishesAdapter = new WishesAdapter();
        mRclWishList.setAdapter(mWishesAdapter);

        mWishRepository.get().subscribe(mWishListSubscriber);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mWishRepository.get().subscribe(mWishListSubscriber);
        });
        return view;
    }

    private void initRepositories() {
        mWishRepository = ((AndroidApplication) getActivity().getApplication()).getApplicationComponent().provideWishListRepository();
    }

    private void initSubscribers() {
        mWishListSubscriber = new Subscriber<WishList>() {
            @Override
            public void onCompleted() {
                Log.i("onCompleted", ":)");
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", e.toString());
            }

            @Override
            public void onNext(WishList wishList) {
                Log.i("onNext", wishList.toString());
                mWishesAdapter.clear();
                mRclWishList.getAdapter().notifyDataSetChanged();

                if (wishList.getItems().size() > 0) {
                    mTxtWishListHint.setVisibility(View.INVISIBLE);
                    mRclWishList.setVisibility(View.VISIBLE);

                    mWishesAdapter.addAll(wishList.getItems());
                    mRclWishList.getAdapter().notifyDataSetChanged();
                } else {
                    mTxtWishListHint.setVisibility(View.VISIBLE);
                    mRclWishList.setVisibility(View.INVISIBLE);
                }

                mWishList = wishList;
            }
        };
    }

    @OnClick(R.id.fab)
    public void addWish() {
        AddWishDialog addWishDialog = new AddWishDialog(getActivity(), wish -> {
            mWishesAdapter.clear();
            mRclWishList.getAdapter().notifyDataSetChanged();

            mWishList.add(wish);
            mWishRepository.update(mWishList).subscribe(mWishListSubscriber);
        });
        addWishDialog.show();
    }
}
