package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
	private RecyclerView mCrimeRecyclerView;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

		mCrimeRecyclerView = v.findViewById(R.id.crime_recycler_view);
		mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mCrimeRecyclerView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

		updateUI();

		return v;
	}

	private void updateUI() {
		CrimeLab crimeLab = CrimeLab.get(getActivity());
		CrimeAdapter adapter = new CrimeAdapter(crimeLab.getCrimes());
		mCrimeRecyclerView.setAdapter(adapter);
	}

	private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private Crime mCrime;
		private TextView mTitleTextView;
		private TextView mDateTextView;

		public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
			super(inflater.inflate(R.layout.list_item_crime, parent, false));

			mTitleTextView = itemView.findViewById(R.id.crime_title);
			mDateTextView = itemView.findViewById(R.id.crime_date);

			itemView.setOnClickListener(this);
		}

		public void bind(Crime crime) {
			mCrime = crime;
			mTitleTextView.setText(mCrime.getTitle());
			mDateTextView.setText(mCrime.getDate().toString());
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(),
					mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
		private List<Crime> mCrimes;

		public CrimeAdapter(List<Crime> crimes) {
			mCrimes = crimes;
		}

		/*@Override
		public int getItemViewType(int position) {
			return mCrimes.get(position).isRequiresPolice() ? ;
		}*/

		@NonNull
		@Override
		public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			return new CrimeHolder(layoutInflater, parent);
		}

		@Override
		public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
			Crime crime = mCrimes.get(position);
			holder.bind(crime);
		}

		@Override
		public int getItemCount() {
			return mCrimes.size();
		}
	}
}
