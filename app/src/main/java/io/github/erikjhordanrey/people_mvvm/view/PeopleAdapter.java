/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.erikjhordanrey.people_mvvm.view;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.erikjhordanrey.people_mvvm.R;
import io.github.erikjhordanrey.people_mvvm.databinding.ItemPeopleBinding;
import io.github.erikjhordanrey.people_mvvm.model.People;
import io.github.erikjhordanrey.people_mvvm.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> implements View.OnClickListener {

    private List<People> peopleList;

    PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @NonNull
    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPeopleBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people, parent, false);
        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.equals(R.id.delete)){
                    peopleList.remove(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        //Log.d("View: ", v.toString());
        //Toast.makeText(v.getContext(), mTextViewTitle.getText() + " position = " + getPosition(), Toast.LENGTH_SHORT).show();
        if(v.equals(R.id.delete)){
            peopleList.remove(0);
        }
    }

    static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding binding;

        PeopleAdapterViewHolder(ItemPeopleBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bindPeople(People people) {
            if (binding.getPeopleViewModel() == null) {
                binding.setPeopleViewModel(new ItemPeopleViewModel(itemView.getContext(), people));
            } else {
                binding.getPeopleViewModel().setPeople(people);
            }
        }
    }
}
