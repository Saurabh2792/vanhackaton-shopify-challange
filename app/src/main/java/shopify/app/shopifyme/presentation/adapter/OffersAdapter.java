package shopify.app.shopifyme.presentation.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.domain.entities.Offer;
import shopify.app.shopifyme.presentation.component.RecyclerListAdapter;

public class OffersAdapter extends RecyclerListAdapter<Offer, OffersAdapter.OfferHolder> {
    private final Activity mActivity;

    public OffersAdapter(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.offer_list_line, parent, false);

        return new OffersAdapter.OfferHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OfferHolder holder, int position) {
        Offer offer = getItem(position);

        holder.mStore.setText(offer.getStore().toUpperCase());
        holder.mWishName.setText(offer.getWish());
        holder.mPrice.setText(format(offer.getPrice()) + " + shipping");
    }

    public class OfferHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.wish)
        TextView mWishName;
        @Bind(R.id.store)
        TextView mStore;
        @Bind(R.id.price)
        TextView mPrice;

        public OfferHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                String url = mStore.getText().toString();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mActivity.startActivity(browserIntent);
            });
        }
    }

    private String format(final int amount) {
        DecimalFormat format = new DecimalFormat("R$ #,##0.00;R$ -#,##0.00");

        BigDecimal decimalAmount = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100L));
        return format.format(decimalAmount);
    }

}
