package nyc.friendlyrobot.sample.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nyc.friendlyrobot.sample.androidboilerplate.R;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    private List<Object> mRibots;

    @Inject
    public RibotsAdapter() {
        mRibots = new ArrayList<>();
    }

    public void setRibots(List<Object> ribots) {
        mRibots = ribots;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RibotViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.view_hex_color) View hexColorView;
        @Bind(R.id.text_name) TextView nameTextView;
        @Bind(R.id.text_email) TextView emailTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
