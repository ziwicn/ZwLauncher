package com.czw.zwlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.czw.zwlauncher.model.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {
    public static List<AppInfo> loadApplications(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> intentActivities = packageManager.queryIntentActivities(mainIntent, 0);
        List<AppInfo> entries = new ArrayList<>();

        if (intentActivities != null) {
            for (ResolveInfo resolveInfo : intentActivities) {
                if (!context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                    entries.add(new AppInfo(packageManager, resolveInfo));
                }
            }
        }

        Collections.sort(entries, new Comparator<AppInfo>() {
            @Override
            public int compare(AppInfo lhs, AppInfo rhs) {
                return lhs.getName().compareToIgnoreCase(rhs.getName());
            }
        });
        return entries;
    }


    public static List<String> loadHead() {
        List<String> list = new ArrayList<>();
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        list.add("哈哈哈");
        return list;
    }
}
