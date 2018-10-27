package utoronto.saturn.app.front_end.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import utoronto.saturn.User;
import utoronto.saturn.app.GuiManager;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.adapters.EventItemFullAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {
    private User user;
    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
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
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        // Setup RecyclerView
        RecyclerView rvEvents = view.findViewById(R.id.rv_events);
        // EventItemFullAdapter adapter = new EventItemFullAdapter(GuiManager.getInstance().getCurrentUser().getAllEvents());
        EventItemFullAdapter adapter = new EventItemFullAdapter(null);
        rvEvents.setAdapter(adapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

}
