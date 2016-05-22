package shopify.app.shopifyme.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.domain.entities.Wish;
import shopify.app.shopifyme.presentation.component.RecyclerListAdapter;

public class WishesAdapter extends RecyclerListAdapter<Wish, WishesAdapter.WishHolder> {
    @Override
    public WishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.wish_list_line, parent, false);

        return new WishesAdapter.WishHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WishHolder holder, int position) {
        Wish wish = getItem(position);

        holder.mWishName.setText(wish.getName().toUpperCase());
    }

    public class WishHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.wish_name)
        TextView mWishName;

        public WishHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
