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

package io.github.erikjhordanrey.people_mvvm.viewmodel;

import android.content.Context;
import android.view.View;
import androidx.databinding.BaseObservable;
import io.github.erikjhordanrey.people_mvvm.model.People;
import io.github.erikjhordanrey.people_mvvm.view.PeopleDetailActivity;

public class ItemPeopleViewModel extends BaseObservable {

    private final Context context;
    private People people;

    public ItemPeopleViewModel(Context context, People people) {
        this.context = context;
        this.people = people;
    }

    public String getFullName() {
        return people.getName();
    }

    public String getPic() {
        return people.getPic();
    }

    public String getDesc() {
        return people.getDesc();
    }

    public String getPrice() {
        return people.getPrice();
    }

    public void onItemClick(View view) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
    }

    public void onDel(View view) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
    }



    public void setPeople(People people) {
        this.people = people;
        notifyChange();
    }
}
