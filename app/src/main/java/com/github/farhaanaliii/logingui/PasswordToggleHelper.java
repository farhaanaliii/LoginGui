package com.github.farhaanaliii.logingui;

import android.text.InputType;
import android.view.MotionEvent;
import android.widget.EditText;

public final class PasswordToggleHelper {

    public static void bind(EditText password_input) {
        final boolean[] is_visible = {false};
        password_input.setOnTouchListener((view, motion_event) -> {
            if (motion_event.getAction() == MotionEvent.ACTION_UP) {
                int extra_padding = password_input.getTotalPaddingRight();
                if (motion_event.getRawX() >= (password_input.getRight() - extra_padding)) {
                    is_visible[0] = !is_visible[0];
                    int eye_drawable = is_visible[0] ? R.drawable.ic_eye_off_outline : R.drawable.ic_eye_outline;
                    int start = password_input.getSelectionStart();
                    int end = password_input.getSelectionEnd();
                    password_input.setInputType(InputType.TYPE_CLASS_TEXT | (is_visible[0] ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
                    password_input.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_key_outline, 0, eye_drawable, 0);
                    password_input.setSelection(start, end);
                    return true;
                }
            }
            return false;
        });
    }
}
