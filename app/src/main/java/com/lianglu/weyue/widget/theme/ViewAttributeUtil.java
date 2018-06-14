package com.lianglu.weyue.widget.theme;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by chengli on 15/6/8.
 */
public class ViewAttributeUtil {

    /**
     * 根据传入的attr获取某个属性(paramInt)的值
     *
     * @param attr
     * @param paramInt 某个属性  比如android.R.attr.background
     */
    public static int getAttributeValue(AttributeSet attr, int paramInt) {
        int value = -1;
        //获取属性个数
        int count = attr.getAttributeCount();
        //循环遍历每个属性
        for (int i = 0; i < count; i++) {
            //返回的是与特定的XML相关联的资源标识符属性名称(比如android.R.attr.background)  如果与paramInt相同,则进入
            if (attr.getAttributeNameResource(i) == paramInt) {  //值形如16842964
                //获取该属性  将指定属性的值作为字符串表示形式进行处理。
                String str = attr.getAttributeValue(i);  //值形如 ?2130968688
                if (null != str && str.startsWith("?")) {
                    value = Integer.valueOf(str.substring(1, str.length()));   //去掉最前面的问号
                    return value;   //值形如 2130968688
                }
            }
        }
        return value;
    }

    public static int getBackgroundAttibute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.background);
    }

    public static int getCheckMarkAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.checkMark);
    }

    public static int getSrcAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.src);
    }

    public static int getTextApperanceAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textAppearance);
    }

    public static int getDividerAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.divider);
    }

    public static int getTextColorAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textColor);
    }

    public static int getTextLinkColorAttribute(AttributeSet attr) {
        return getAttributeValue(attr, android.R.attr.textColorLink);
    }

    /**
     * 读取background并设置到view上
     */
    public static void applyBackgroundDrawable(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        //返回一个与 attrs 中列举出的属性相关的数组，数组里面的值由 Theme 指定
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        if (ta != null && ta.length() == 1) {
            Drawable drawable = ta.getDrawable(0);
            if (null != ci) {
                (ci.getView()).setBackgroundDrawable(drawable);
            }
            ta.recycle();
        }

    }

    public static void applyImageDrawable(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != ci && ci instanceof ImageView) {
            ((ImageView) ci.getView()).setImageDrawable(drawable);
        }
        ta.recycle();
    }

    public static void applyTextAppearance(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getResourceId(0, 0);
        if (null != ci && ci instanceof TextView) {
            ((TextView) ci.getView()).setTextAppearance(ci.getView().getContext(), resourceId);
        }
        ta.recycle();
    }

    public static void applyTextColor(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getColor(0, 0);
        if (null != ci && ci instanceof TextView) {
            ((TextView) ci.getView()).setTextColor(resourceId);
        }
        ta.recycle();
    }

    public static void applyTextLinkColor(ColorUiInterface ci, Resources.Theme theme, int paramInt) {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getColor(0, 0);
        if (null != ci && ci instanceof TextView) {
            ((TextView) ci.getView()).setLinkTextColor(resourceId);
        }
        ta.recycle();
    }

}
