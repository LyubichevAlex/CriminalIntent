package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new LinkedList<>();
        // test data
        for (int i = 0; i < 100; ++i) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public @Nullable Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals((id))) {
                return crime;
            }
        }

        return null;
    }
}