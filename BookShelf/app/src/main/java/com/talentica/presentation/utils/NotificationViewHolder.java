package com.talentica.presentation.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.talentica.databinding.SingleNotificationLayoutBinding;
import com.talentica.presentation.leadCapturePage.notifications.model.NotificationModel;

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    private static final String Tag = "NotificationViewHolder";
    private SingleNotificationLayoutBinding binding;

    private NotificationViewHolder(SingleNotificationLayoutBinding binding, AppCompatActivity activity) {
        super(binding.getRoot());
        // binding.getRoot().setLayoutParams(new LinearLayout.LayoutParams(Util.getDeviceWidth(activity) / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.binding = binding;
    }

    public static NotificationViewHolder create(LayoutInflater inflater, ViewGroup parent, AppCompatActivity activity) {
        Log.e(Tag, "BookViewHolder create ");
        SingleNotificationLayoutBinding binding = SingleNotificationLayoutBinding.inflate(inflater, parent, false);
        return new NotificationViewHolder(binding, activity);
    }

    public void bindTo(NotificationModel notificationModel) {
        String returnDue = "Return due " + (notificationModel.getDueToOrFrom() ? "To" : "From");
        binding.setNotificationModel(notificationModel);
        binding.returnDue.setText(Html.fromHtml(getColoredSpanned(returnDue, "#999999")));
        binding.returnDue.append("  " + notificationModel.getReturnDueToOrFrom());
        binding.executePendingBindings();
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

}
