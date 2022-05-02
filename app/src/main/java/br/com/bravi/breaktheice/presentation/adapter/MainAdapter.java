package br.com.bravi.breaktheice.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.bravi.breaktheice.R;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final List<ActivityModel> _activityModelDataSet;
    private final Context _context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView _accessibilityTextView;
        private final TextView _activityTextView;
        private final TextView _keyTextView;
        private final TextView _participantsTextView;

        public ViewHolder(View view) {
            super(view);

            _accessibilityTextView = view.findViewById(R.id.accessibility_text_view);
            _activityTextView = view.findViewById(R.id.activity_text_view);
            _keyTextView = view.findViewById(R.id.key_text_view);
            _participantsTextView = view.findViewById(R.id.participants_text_view);
        }

        public TextView getAccessibilityTextView() {
            return _accessibilityTextView;
        }

        public TextView getActivityTextView() {
            return _activityTextView;
        }

        public TextView getKeyTextView() {
            return _keyTextView;
        }

        public TextView getParticipantsTextView() {
            return _participantsTextView;
        }
    }

    public MainAdapter(List<ActivityModel> activityModelDataSet, Context context) {
        _activityModelDataSet = activityModelDataSet;
        _context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_activity, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ActivityModel activityModel = _activityModelDataSet.get(position);
        viewHolder.getAccessibilityTextView().setText(getAccessibility(activityModel.getAccessibility()));
        viewHolder.getActivityTextView().setText(activityModel.getActivity());
        viewHolder.getKeyTextView().setText(_context.getString(R.string.label_key, activityModel.getKey()));
        viewHolder.getParticipantsTextView().setText(_context.getString(R.string.label_participants, activityModel.getParticipants()));
    }

    @Override
    public int getItemCount() {
        return _activityModelDataSet.size();
    }

    public String getAccessibility(float accessibility) {
        if (accessibility >= 0 && accessibility <= 3.3) {
            return _context.getString(R.string.label_accessibility_easy);
        } else if (accessibility >= 3.3 && accessibility <= 6.6) {
            return _context.getString(R.string.label_accessibility_medium);
        } else {
            return _context.getString(R.string.label_accessibility_hard);
        }
    }
}
