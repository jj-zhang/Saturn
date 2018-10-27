package utoronto.saturn.app.front_end.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import utoronto.saturn.app.GuiManager;
import utoronto.saturn.app.R;
import utoronto.saturn.app.front_end.adapters.ArtistItemAdapter;
import utoronto.saturn.app.front_end.viewmodels.FollowingViewModel;
import android.arch.lifecycle.ViewModelProviders;

public class FollowingFragment extends Fragment {

    private FollowingViewModel mViewModel;

    public static FollowingFragment newInstance() {
        return new FollowingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        mViewModel = ViewModelProviders.of(this).get(FollowingViewModel.class);

        // Setup RecyclerView
        RecyclerView rvEvents = view.findViewById(R.id.rv_following);
        //ArtistItemAdapter adapter = new ArtistItemAdapter(GuiManager.getInstance().getCurrentUser().getFollowedCreatorList());
        ArtistItemAdapter adapter = new ArtistItemAdapter(null);
        rvEvents.setAdapter(adapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
