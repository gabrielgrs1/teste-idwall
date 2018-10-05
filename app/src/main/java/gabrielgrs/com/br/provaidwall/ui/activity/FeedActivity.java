package gabrielgrs.com.br.provaidwall.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.irozon.sneaker.Sneaker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gabrielgrs.com.br.provaidwall.DogsViewerApplication;
import gabrielgrs.com.br.provaidwall.R;
import gabrielgrs.com.br.provaidwall.service.api.feed.FeedDto;
import gabrielgrs.com.br.provaidwall.service.api.feed.FeedRepository;
import gabrielgrs.com.br.provaidwall.ui.adapter.DogsListAdapter;

public class FeedActivity extends GenericActivity implements FeedRepository.FeedServiceListener {

    public static final int HUSKY_POSITION = 0;
    public static final int HOUND_POSITION = 1;
    public static final int PUG_POSITION = 2;
    public static final int LABRADOR_POSITION = 3;
    public static final int DOGS_COLUNM_QUANTITY = 3;
    public static final String HOUND_TEXT = DogsViewerApplication.getInstance().getString(R.string.feed_hound_textivew);
    public static final String HUSKY_TEXT = DogsViewerApplication.getInstance().getString(R.string.feed_husky_textivew);
    public static final String PUG_TEXT = DogsViewerApplication.getInstance().getString(R.string.feed_pug_textivew);
    public static final String LABRADOR_TEXT = DogsViewerApplication.getInstance().getString(R.string.feed_labrador_textivew);

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    @BindView(R.id.feed_dogs_recyclerview)
    RecyclerView mFeedDogsRecyclerView;

    @BindView(R.id.feed_title_textview)
    TextView mFeedTitleTextView;

    private DogsListAdapter mDogsListAdapter;
    private List<String> mDogImageLinkList;
    private AlertDialog alertDialog;

    //TODO IMPLEMENTAR BOTAO SAIR
    //TODO IMPLEMENTAR O DAGGER
    //TODO COLOCAR UM DIMENS DO TAMANHO DAS IMAGEVIEWS
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
    }

    @Override
    public void loadingMethods() {
        loadBottomBar();
        configureDogImageLinkList();
        configureDogImageRecyclerView();
        getDefaultDog();
        configureAlertDialog();
    }


    @Override
    public void response(FeedDto feedDto) {
        resetDogsListBy(feedDto);
        configureDogImageRecyclerView();
    }

    @Override
    public void startLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void serverError(String message) {
        Sneaker.with(this)
                .setTitle(DogsViewerApplication.getInstance().getString(R.string.generic_erro_title))
                .setDuration(8000)
                .setMessage(message)
                .sneakError();
    }

    @Override
    public void onBackPressed() {
        alertDialog.show();

    }

    private void resetDogsListBy(FeedDto feedDto) {
        mDogImageLinkList.clear();
        mDogImageLinkList.addAll(feedDto.getList());
    }

    private void getDefaultDog() {
        setTitleBy(HUSKY_POSITION);
        feedService(HUSKY_TEXT);
    }

    private void configureDogImageRecyclerView() {
        mFeedDogsRecyclerView.setLayoutManager(new GridLayoutManager(this, DOGS_COLUNM_QUANTITY));
        mDogsListAdapter = new DogsListAdapter(this, mDogImageLinkList);
        mFeedDogsRecyclerView.setAdapter(mDogsListAdapter);
    }

    private void configureDogImageLinkList() {
        mDogImageLinkList = new ArrayList<>();
    }

    private void loadBottomBar() {
        configureBottomBarItens();
        configureBottomBarStyle();
        configureBottomBar();
        setOnTabSelectListener();
    }

    private void setOnTabSelectListener() {
        mBottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            setTitleBy(position);
            getImagesBy(position);

            return true;
        });
    }

    private void getImagesBy(int position) {
        feedService(getDogSelectedBy(position));
    }

    private String getDogSelectedBy(int position) {
        if (position == HUSKY_POSITION) {
            return HUSKY_TEXT;
        } else if (position == HOUND_POSITION) {
            return HOUND_TEXT;
        } else if (position == PUG_POSITION) {
            return PUG_TEXT;
        }
        return LABRADOR_TEXT;
    }

    private void setTitleBy(int position) {
        if (position == HUSKY_POSITION) {
            mFeedTitleTextView.setText(R.string.feed_husky_textivew);
        } else if (position == HOUND_POSITION) {
            mFeedTitleTextView.setText(R.string.feed_hound_textivew);
        } else if (position == PUG_POSITION) {
            mFeedTitleTextView.setText(R.string.feed_pug_textivew);
        } else if (position == LABRADOR_POSITION) {
            mFeedTitleTextView.setText(R.string.feed_labrador_textivew);
        }
    }

    private void configureBottomBar() {
        mBottomNavigation.setBehaviorTranslationEnabled(true);
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setCurrentItem(HUSKY_POSITION);
    }

    private void configureBottomBarStyle() {
        mBottomNavigation.setDefaultBackgroundColor(DogsViewerApplication.getInstance().getColor(R.color.colorBackgroundBottomBar));
        mBottomNavigation.setAccentColor(DogsViewerApplication.getInstance().getColor(R.color.colorBackgroundActiveBottomBarItem));
        mBottomNavigation.setInactiveColor(DogsViewerApplication.getInstance().getColor(R.color.colorBackgroundInactiveBottomBarItem));
    }

    private void configureBottomBarItens() {
        AHBottomNavigationItem itemHusky = new AHBottomNavigationItem(HUSKY_TEXT, R.drawable.husky);
        AHBottomNavigationItem itemHound = new AHBottomNavigationItem(HOUND_TEXT, R.drawable.hound);
        AHBottomNavigationItem itemPug = new AHBottomNavigationItem(PUG_TEXT, R.drawable.pug);
        AHBottomNavigationItem itemLabrador = new AHBottomNavigationItem(LABRADOR_TEXT, R.drawable.beagle);

        mBottomNavigation.addItem(itemHusky);
        mBottomNavigation.addItem(itemHound);
        mBottomNavigation.addItem(itemPug);
        mBottomNavigation.addItem(itemLabrador);
    }

    private void feedService(String category) {
        if (checkInternet()) {
            new FeedRepository(this).getLinkImages(category);
        }
    }

    private void configureAlertDialog() {
        AlertDialog.Builder builder = configureAlertBuilder();
        alertDialog = builder.create();
    }

    @NonNull
    private AlertDialog.Builder configureAlertBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FeedActivity.this);
        builder.setTitle(R.string.feed_exit_dialog_title);
        builder.setMessage(R.string.feed_exit_dialog_message);
        builder.setPositiveButton(R.string.feed_exit_dialog_positive_button, (dialogInterface, i) -> {
            closeApplication();
        });

        builder.setNegativeButton(R.string.feed_exit_dialog_negative_button, (dialogInterface, i) -> alertDialog.dismiss());
        builder.setCancelable(true);
        return builder;
    }
s
    private void closeApplication() {
        finishAffinity();
        System.exit(0);
    }
}
