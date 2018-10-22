package utoronto.saturn.app.front_end.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import utoronto.saturn.Event;
import utoronto.saturn.app.R;

public class EventItemFullAdapter extends
        RecyclerView.Adapter<EventItemFullAdapter.ViewHolder>{
    private List<Event> mEvents;

    public EventItemFullAdapter(List<Event> events) {
        mEvents = events;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView dateTextView;
        public TextView eventNameTextView;
        public TextView locationTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            dateTextView = itemView.findViewById(R.id.text_date);
            eventNameTextView = itemView.findViewById(R.id.text_event_name);
            locationTextView = itemView.findViewById(R.id.text_location);
        }
    }

    @NonNull
    public EventItemFullAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View eventItemView = inflater.inflate(R.layout.layout_eventitem_full, parent, false);

        ViewHolder viewHolder = new ViewHolder(eventItemView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull EventItemFullAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        if (mEvents == null || mEvents.size() == 0) {
            TextView dateTextView = viewHolder.dateTextView;
            dateTextView.setText("");
            TextView eventNameTextView = viewHolder.eventNameTextView;
            eventNameTextView.setText(R.string.event_noevents_message);
            TextView locationTextView = viewHolder.locationTextView;
            locationTextView.setText("");
        }
        else {
            Event event = mEvents.get(position);

            // Set item views based on your views and data model
            TextView dateTextView = viewHolder.dateTextView;
            dateTextView.setText(String.valueOf(event.getReleaseDate()));
            TextView eventNameTextView = viewHolder.eventNameTextView;
            eventNameTextView.setText(event.getName());
            TextView locationTextView = viewHolder.locationTextView;
            locationTextView.setText(event.getDescription());
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        if (mEvents == null || mEvents.size() == 0) return 1;
        return mEvents.size();
    }
}
