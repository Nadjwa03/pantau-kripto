package com.example.pantaukripto.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.pantaukripto.R;

public class TagButton extends LinearLayout {
    private TextView tagText;
    private ImageView tagIcon;

    public TagButton(Context context) {
        super(context);
        init(context, null);
    }

    public TagButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TagButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.tag_button, this, true);
        setOrientation(HORIZONTAL);

        tagText = findViewById(R.id.tag_button_text);
        tagIcon = findViewById(R.id.tag_button_icon);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.TagButton,
                    0, 0);

            try {
                String text = a.getString(R.styleable.TagButton_tagText);
                Drawable icon = a.getDrawable(R.styleable.TagButton_tagIcon);

                setTagText(text);
                setTagIcon(icon);
            } finally {
                a.recycle();
            }
        }
    }

    public void setTagText(String text) {
        tagText.setText(text);
    }

    public void setTagIcon(Drawable icon) {
        tagIcon.setImageDrawable(icon);
    }

    public void setTagIcon(int resId) {
        tagIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), resId));
    }
}
