package com.books.sample.shared.utils.infrastructure;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

public class UiUtils {
    /**
     * Converting dp to pixel
     */
    public static int dpToPx(int dp, Resources r) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static void setVisible(boolean visible, View firstView, View... otherViews) {
        int visibility = visible ? VISIBLE : GONE;

        for (View view : mergeAndCheckVarargs(firstView, otherViews)) {
            view.setVisibility(visibility);
        }
    }

    private static <T> List<T> mergeAndCheckVarargs(T firstItem, T[] otherItems) {
        checkNotNull(firstItem);
        checkNotNull(otherItems);
        checkArgument(!asList(otherItems).contains(null));

        List<T> result = newArrayList(otherItems);
        result.add(0, firstItem);

        return result;
    }
}
