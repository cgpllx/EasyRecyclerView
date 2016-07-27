package cc.easyandroid.easyrecyclerview.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cc.easyandroid.easyrecyclerview.EasyRecyclerAdapter;
import cc.easyandroid.easyrecyclerview.EasyRecyclerView;
import cc.easyandroid.easyrecyclerview.core.DefaultFooterHander;
import cc.easyandroid.easyrecyclerview.core.DefaultHeaderHander;
import cc.easyandroid.easyrecyclerview.demo.dummy.DummyContent;
import cc.easyandroid.easyrecyclerview.demo.dummy.DummyContent.DummyItem;
import cc.easyandroid.easyrecyclerview.listener.OnLoadMoreListener;
import cc.easyandroid.easyrecyclerview.listener.OnRefreshListener;

public class ListFragment extends Fragment {
    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        EasyRecyclerView easyRecyclerView= (EasyRecyclerView) view.findViewById(R.id.list);
        if (true) {
            Context context = view.getContext();
            final EasyRecyclerView recyclerView = (EasyRecyclerView) easyRecyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //  recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            final MyAdapter adapter = new MyAdapter(DummyContent.ITEMS);
            adapter.clear();

           ViewGroup viewGroup= (ViewGroup) recyclerView.getParent();
            View empty = View.inflate(getContext(), R.layout.empty, null);
            viewGroup.addView(empty);
//            View empty =view.findViewById(R.id.emptyView);
            recyclerView.setEmptyView(empty);
            recyclerView.setAdapter(adapter);
            recyclerView.setHeaderHander(new DefaultHeaderHander(getContext()));
            recyclerView.setFooterHander(new DefaultFooterHander(getContext()));
            recyclerView.setLoadMoreEnabled(false);
            toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
            adapter.setOnItemClickListener(new EasyRecyclerAdapter.OnItemClickListener<DummyItem>() {
                @Override
                public void onItemClick(int position, DummyItem data) {
                    if (toast != null) {
                        toast.setText(position + "");
                    } else {
                        toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                }
            });

            recyclerView.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    recyclerView.finishLoadMore();
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setDatas(DummyContent.ITEMS);
                            recyclerView.finishRefresh(true);
                            recyclerView.setLoadMoreEnabled(true);
                        }
                    }, 3000);
                }

            });
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.autoRefresh();
                }
            });
            recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(final EasyRecyclerView.FooterHander loadMoreView) {
                    recyclerView.finishRefresh(true);
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addDatas(DummyContent.ITEMS);
                            loadMoreView.fullLoadCompleted();

                        }
                    }, 3000);
                }
            });

        }
        return view;
    }


}
