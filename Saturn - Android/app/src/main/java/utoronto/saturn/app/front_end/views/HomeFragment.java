package utoronto.saturn.app.front_end.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import utoronto.saturn.Event;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.adapters.EventItemFullAdapter;
import utoronto.saturn.app.front_end.listeners.OnItemClickListener;
import utoronto.saturn.app.front_end.viewmodels.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    List<Object> events;

    private HomeViewModel mViewModel;

    private OnItemClickListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        // Set up recycler views
        RecyclerView rvEventsComingUp = view.findViewById(R.id.rv_events_coming_up);
        EventItemFullAdapter adapter = new EventItemFullAdapter(mViewModel.getMyEvents(), mListener);
        rvEventsComingUp.setAdapter(adapter);
        rvEventsComingUp.setLayoutManager(new LinearLayoutManager(this.getContext()));
        RecyclerView rvSuggestions = view.findViewById(R.id.rv_suggestions);
        adapter = new EventItemFullAdapter(mViewModel.getSuggestedEvents(), mListener);
        rvSuggestions.setAdapter(adapter);
        rvSuggestions.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onEventClicked(Event event) {
        if (mListener != null) {
            mListener.onItemClick(event);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
