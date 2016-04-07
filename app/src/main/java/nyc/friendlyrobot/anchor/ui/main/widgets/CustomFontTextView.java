package nyc.friendlyrobot.anchor.ui.main.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import nyc.friendlyrobot.anchor.androidboilerplate.R;
import nyc.friendlyrobot.anchor.ui.utils.TypefaceCache;
import timber.log.Timber;

/**
 * Created by brianplummer on 2/20/16.
 */
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.TextView);
    }

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.ConstructorCallsOverridableMethod"})
    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        String fontName = null;
        TypedArray appearance = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);

        if (appearance != null) {
            int indexCount = appearance.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int attr = appearance.getIndex(i);
                if (attr == R.styleable.CustomFontTextView_font) {
                    fontName = appearance.getString(attr);
                }
            }
            appearance.recycle();
        }
        setTypeface(fontName);
    }

    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public void setTypeface(@Nullable String typefaceName) {

        if (typefaceName == null) {
            return;
        }

        Context context = getContext();
        Typeface font = TypefaceCache.get(typefaceName);
        if (font == null) {
            try {
                font = Typeface.createFromAsset(context.getAssets(), typefaceName);
            } catch (Exception e) {
                Timber.e(CustomFontTextView.class.getSimpleName(),
                        String.format("Error loading font: %s. Reverting to system default.", typefaceName), e);
                return;
            }
            TypefaceCache.put(typefaceName, font);
        }
        setTypeface(font);
    }

}
