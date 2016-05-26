package cc.guoxingnan.fragmentrefreshtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {
    private ListView listView;
    private SwipeRefreshLayout refreshLayout;
    private List<String> data;
    private ArrayAdapter<String> adapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        context = getActivity();
        initView(view);
        initData();
        setListener();

        return view;
    }

    private void initData() {
        data = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            data.add("第一页内容： "+ i + " " + (char) i);
        }
        adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,data);
        listView.setAdapter(adapter);
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.add(0,"new data " +System.currentTimeMillis());
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(android.R.color.holo_red_light,android.R.color.holo_blue_dark);
    }


}
