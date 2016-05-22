package shopify.app.shopifyme.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.data.repository.OfferRepository;
import shopify.app.shopifyme.domain.entities.OfferList;
import shopify.app.shopifyme.presentation.adapter.OffersAdapter;
import shopify.app.shopifyme.presentation.application.AndroidApplication;

public class OffersFragment extends android.support.v4.app.Fragment {

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.offers_list)
    RecyclerView mRclOffersList;
    private RecyclerView.LayoutManager mLayoutManager;

    private OfferRepository mOfferRepository;
    private Subscriber<OfferList> mOfferListSubscriber;

    private OffersAdapter mOffersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_offers_list, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRclOffersList.setLayoutManager(mLayoutManager);

        initRepositories();
        initSubscribers();

        mOffersAdapter = new OffersAdapter(getActivity());
        mRclOffersList.setAdapter(mOffersAdapter);

        mOfferRepository.get().subscribe(mOfferListSubscriber);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mOfferRepository.get().subscribe(mOfferListSubscriber);
        });
        return view;
    }

    private void initRepositories() {
        mOfferRepository = ((AndroidApplication) getActivity().getApplication()).getApplicationComponent().provideOfferListRepository();
    }

    private void initSubscribers() {
        mOfferListSubscriber = new Subscriber<OfferList>() {
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
            public void onNext(OfferList offerList) {
                Log.i("onNext", offerList.toString());
                mOffersAdapter.clear();
                mRclOffersList.getAdapter().notifyDataSetChanged();

                mOffersAdapter.addAll(offerList.getItems());
                mRclOffersList.getAdapter().notifyDataSetChanged();
            }
        };
    }
}
