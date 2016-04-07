package nyc.friendlyrobot.anchor.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nyc.friendlyrobot.anchor.androidboilerplate.R;
import nyc.friendlyrobot.anchor.data.SyncService;
import nyc.friendlyrobot.anchor.data.model.Loo;
import nyc.friendlyrobot.anchor.ui.base.BaseActivity;
import nyc.friendlyrobot.anchor.util.DialogFactory;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "nyc.friendlyrobot.sample.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject MainPresenter presenter;
    @Inject RibotsAdapter adapter;


    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.attachView(this);
        presenter.loadFoos();

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showLoo(List<Loo> loos) {
        adapter.setLoos(loos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

}
