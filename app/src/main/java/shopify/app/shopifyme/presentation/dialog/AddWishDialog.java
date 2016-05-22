package shopify.app.shopifyme.presentation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.domain.entities.Wish;
import shopify.app.shopifyme.domain.entities.listeners.OnAddWish;

public class AddWishDialog extends Dialog {
    @Bind(R.id.add)
    Button mBtnAdd;
    @Bind(R.id.cancel)
    Button mBtnCancel;
    @Bind(R.id.product)
    EditText mEdtWish;

    private OnAddWish mOnAddWish;

    public AddWishDialog(Context context, OnAddWish onAddWish) {
        super(context);
        init();
        mOnAddWish = onAddWish;
    }


    private void init() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.white);
        setContentView(R.layout.dialog_add_wish);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        dismiss();
    }

    @OnClick(R.id.add)
    public void add() {
        if (mEdtWish.getText().toString().isEmpty())
            return;

        mOnAddWish.add(new Wish(mEdtWish.getText().toString()));
        dismiss();
    }
}
