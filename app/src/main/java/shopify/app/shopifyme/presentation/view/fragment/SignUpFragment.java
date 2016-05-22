package shopify.app.shopifyme.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;
import shopify.app.shopifyme.data.repository.LoginRepository;
import shopify.app.shopifyme.presentation.Actions;
import shopify.app.shopifyme.presentation.application.AndroidApplication;
import shopify.app.shopifyme.presentation.view.activity.WishListActivity;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
    }

    @Bind(R.id.edtEmail)
    EditText mEdtEmail;
    @Bind(R.id.edtPassword)
    EditText mEdtPassword;
    @Bind(R.id.edtConfirmPassword)
    EditText mEdtConfirmPassword;

    private LoginRepository mLoginRepository;
    private Subscriber<Void> mLoginSubscriber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        initRepositories();
        initSubscribers();

        return view;
    }

    private void initRepositories() {
        mLoginRepository = ((AndroidApplication) getActivity().getApplication()).getApplicationComponent().provideLoginRepository();
    }

    private void initSubscribers() {
        mLoginSubscriber = new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                toWishList();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void voidz) {

            }
        };
    }

    private void toWishList(){
        if(ShopifyMeSharedPreferences.getInstance().isLogged()){
            Actions.to(getActivity(), WishListActivity.class, true);
        }
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        getActivity().finish();
    }

    @OnClick(R.id.confirm)
    public void confirm() {
        if (!mEdtPassword.getText().toString().equals(mEdtConfirmPassword.getText().toString()))
            return;

        String username = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();


        mLoginRepository.login(username, password).subscribe(mLoginSubscriber);
    }
}