package com.a2340.shelterhelper;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanskriti on 3/14/18.
 */

@SuppressWarnings("ALL")
public class ShelterAdapter extends ArrayAdapter<Shelter> implements Filterable {

    private final ArrayList<Shelter> shelterArray;
    public static List<Shelter> origShelterArray;
    public static List<Shelter> filteredArrList;

    public ShelterAdapter(Context context, ArrayList<Shelter> list) {
        super(context, 0, list);
        this.shelterArray = list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.array_adapter_item,
                    parent, false);
        }

        Shelter shelter = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.shelter_name);
        TextView capacity = (TextView) listItemView.findViewById(R.id.shelter_capacity);
        TextView gender = (TextView) listItemView.findViewById(R.id.shelter_gender);

        assert shelter != null;
        name.setText(shelter.name);
        capacity.setText("Capacity: " + shelter.capacity);
        gender.setText("Restricted to: " + shelter.restrictions);

        return listItemView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                shelterArray.clear();
                shelterArray.addAll((ArrayList<Shelter>) results.values);
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                final FilterResults results = new FilterResults();
                // Holds the results of a filtering operation in values
                filteredArrList = new ArrayList();

                if (origShelterArray == null) {
                    origShelterArray = new ArrayList(shelterArray);
                    // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the
                 *  mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if ((constraint == null) || (constraint.length() == 0)) {

                    // set the Original result to return
                    results.count = origShelterArray.size();
                    results.values = origShelterArray;
                } else {
                    final String constraint_string = constraint.toString().toLowerCase();

                    for (int i = 0; i < origShelterArray.size(); i++) {
                        String data;
                        if (SearchActivity.filterCategory.equals("gender")) {
                            data = origShelterArray.get(i).restrictions.toLowerCase();
                            if (constraint_string.equals("male")) {
                                if(data.contains("men") && !data.contains("women")) {
                                    filteredArrList.add(origShelterArray.get(i));
                                }
                            } else if (constraint_string.equals("female")) {
                                if (data.contains("women")) {
                                    filteredArrList.add(origShelterArray.get(i));
                                }
                            }
                        } else if (SearchActivity.filterCategory.equals("age")) {
                            data = origShelterArray.get(i).restrictions.toLowerCase();
                            if(data.contains(constraint_string)) {
                                filteredArrList.add(origShelterArray.get(i));
                            }
                        } else if (SearchActivity.filterCategory.equals("name")){
                            data = origShelterArray.get(i).name.toLowerCase();
                            if(data.contains(constraint_string)) {
                                filteredArrList.add(origShelterArray.get(i));
                            }
                        }
                    }

                    /*filteredArrList.sort(new Comparator<Shelter>() {
                        @Override
                        public int compare(Shelter s1, Shelter s2) {
                            String s1_data;
                            String s2_data;

                            switch (filterCategory) {
                                case AGE:
                                    s1_data = s1.getAgeCategory().toString().toLowerCase();
                                    s2_data = s2.getAgeCategory().toString().toLowerCase();
                                    break;
                                case GENDER:
                                    s1_data = s1.getGenderCategory().toString().toLowerCase();
                                    s2_data = s2.getGenderCategory().toString().toLowerCase();
                                    break;
                                default:
                                    s1_data = s1.getName().toLowerCase();
                                    s2_data = s2.getName().toLowerCase();
                            }

                            if (filterCategory == FilterCategories.GENDER ?
                                    s1_data.startsWith(constraint_string) :
                                    s1_data.contains(constraint_string)) {

                                if (!(filterCategory == FilterCategories.GENDER ?
                                        s2_data.startsWith(constraint_string) :
                                        s2_data.contains(constraint_string))) {
                                    return -1;
                                }
                                return s1.getName().toLowerCase()
                                .compareTo(s2.getName().toLowerCase());

                            } else if (filterCategory == FilterCategories.GENDER ?
                                    s2_data.startsWith(constraint_string) :
                                    s2_data.contains(constraint_string)) {
                                return 1;
                            }

                            int diff = FuzzySearch.tokenSetRatio(constraint_string, s2_data) -
                                    FuzzySearch.tokenSetRatio(constraint_string, s1_data);
                            if (diff == 0) {
                                return s1.getName()
                                .toLowerCase().compareTo(s2.getName().toLowerCase());
                            }

                            return diff;
                        }
                    });*/

                    // set the Filtered result to return
                    results.count = filteredArrList.size();
                    results.values = filteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

}
