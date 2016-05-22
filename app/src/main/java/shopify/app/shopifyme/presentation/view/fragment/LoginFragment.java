package shopify.app.shopifyme.presentation.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import shopify.app.shopifyme.R;
import shopify.app.shopifyme.data.ShopifyMeSharedPreferences;
import shopify.app.shopifyme.data.repository.LoginRepository;
import shopify.app.shopifyme.presentation.Actions;
import shopify.app.shopifyme.presentation.application.AndroidApplication;
import shopify.app.shopifyme.presentation.view.activity.SignUpActivity;
import shopify.app.shopifyme.presentation.view.activity.WishListActivity;
import shopify.app.shopifyme.presentation.view.animation.Fade;
import shopify.app.shopifyme.presentation.view.animation.Pixels;

public class LoginFragment extends Fragment {

    @Bind(R.id.logo)
    ImageView mImgLogo;
    @Bind(R.id.content)
    RelativeLayout mLytContent;

    @Bind(R.id.edtEmail)
    EditText mEdtEmail;
    @Bind(R.id.edtPassword)
    EditText mEdtPassword;

    @Bind(R.id.signUp)
    TextView mTxtSignUp;

    @Bind(R.id.btnLogin)
    Button mBtnLogin;

    private LoginRepository mLoginRepository;
    private Subscriber<Void> mLoginSubscriber;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        toWishList();

        initRepositories();
        initSubscribers();
        startAnimation();

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

    private void startAnimation() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                moveLogo();

                Fade.in(mLytContent, Fade.ONE_SECOND, Fade.ONE_AND_HALF_SECOND);
                mEdtEmail.requestFocus();
            }

            private void moveLogo() {
                Animation animation = new Animation() {

                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImgLogo.getLayoutParams();

                        if (interpolatedTime > 0) {
                            params.topMargin = (int) (Pixels.convertDpToPixel(100.0f, getActivity()) - (Pixels.convertDpToPixel(100.0f, getActivity()) * interpolatedTime));
                            mImgLogo.setLayoutParams(params);
                        }
                    }
                };
                animation.setDuration(Fade.ONE_SECOND);
                animation.setStartOffset(Fade.ONE_AND_HALF_SECOND);
                mImgLogo.startAnimation(animation);
            }
        });
    }

    private void toWishList(){
        if(ShopifyMeSharedPreferences.getInstance().isLogged()){
            Actions.to(getActivity(), WishListActivity.class, true);
        }
    }

    @OnClick(R.id.btnLogin)
    public void doLogin() {
        String username = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();

        mLoginRepository.login(username, password).subscribe(mLoginSubscriber);
    }

    @OnClick(R.id.signUp)
    public void doSignUp() {
        Actions.to(getActivity(), SignUpActivity.class, false);
    }
}
