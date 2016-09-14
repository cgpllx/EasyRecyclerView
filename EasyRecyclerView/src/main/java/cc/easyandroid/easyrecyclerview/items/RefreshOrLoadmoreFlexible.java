/*
 * Copyright 2016 Davide Steduto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.easyandroid.easyrecyclerview.items;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;

/**
 * 一个空接口，用来区分是header
 */
public class RefreshOrLoadmoreFlexible extends AbstractEasyFlexibleItem {
    private View mRefreshOrLoadmore;

    public RefreshOrLoadmoreFlexible(View refreshOrLoadmore) {
        this.mRefreshOrLoadmore = refreshOrLoadmore;
    }

    @Override
    public int getLayoutRes() {
        return hashCode();
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(EasyFlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerView.ViewHolder(mRefreshOrLoadmore) {
        };
    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter adapter, RecyclerView.ViewHolder holder, int position, List payloads) {
        //not use
    }
}