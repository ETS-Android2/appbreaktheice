package br.com.bravi.breaktheice.presentation.adapter;

import static br.com.bravi.breaktheice.util.GeneralUtil.getAccessibilityText;

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
    private final IItemClickListener _itemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView _accessibilityTextView;
        private final TextView _activityTextView;
        private final TextView _keyTextView;
        private final TextView _participantsTextView;
        private final IItemClickListener _itemClickListener;

        public ViewHolder(View view, IItemClickListener iiTemClickListener) {
            super(view);

            _accessibilityTextView = view.findViewById(R.id.accessibility_text_view);
            _activityTextView = view.findViewById(R.id.activity_text_view);
            _keyTextView = view.findViewById(R.id.key_text_view);
            _participantsTextView = view.findViewById(R.id.participants_text_view);
            _itemClickListener = iiTemClickListener;

            itemView.setOnClickListener(ViewHolder.this);
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

        @Override
        public void onClick(View view) {
            _itemClickListener.onClick(view, getAdapterPosition());
        }
    }


    public MainAdapter(
            Context context,
            List<ActivityModel> activityModelDataSet,
            IItemClickListener iiTemClickListener
    ) {
        _activityModelDataSet = activityModelDataSet;
        _context = context;
        _itemClickListener = iiTemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_activity, viewGroup, false);

        return new ViewHolder(view, _itemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final ActivityModel activityModel = _activityModelDataSet.get(position);
        viewHolder.getAccessibilityTextView().setText(getAccessibilityText(_context, activityModel.getAccessibility()));
        viewHolder.getActivityTextView().setText(activityModel.getActivity());
        viewHolder.getKeyTextView().setText(_context.getString(R.string.label_key, activityModel.getKey()));
        viewHolder.getParticipantsTextView().setText(_context.getString(R.string.label_participants, activityModel.getParticipants()));
    }

    @Override
    public int getItemCount() {
        return _activityModelDataSet.size();
    }
}
