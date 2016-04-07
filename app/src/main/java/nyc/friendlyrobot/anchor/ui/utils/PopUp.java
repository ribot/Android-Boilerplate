package nyc.friendlyrobot.anchor.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import javax.inject.Inject;

import nyc.friendlyrobot.anchor.injection.ScopeActivity;

/**
 * Created by brianplummer on 3/24/16.
 */
@ScopeActivity
public class PopUp {

    private Resources resources;
    private ViewGroup group;
    private LayoutInflater layoutInflater;
    private Context context;

    @Inject
    public PopUp(Activity context) {
        group = (ViewGroup) context.getWindow().getDecorView().getRootView();
        resources = context.getResources();
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void show(int menuId, View anchor, PopupMenu.OnMenuItemClickListener listener) {

//        PopupMenu popupMenu = new PopupMenu(context, anchor, Gravity.LEFT);
//        popupMenu.inflate(menuId);
//        popupMenu.setOnMenuItemClickListener(listener);
//        popupMenu.show();

        /*View menu = layoutInflater
                .inflate(R.layout.payload_menu_layout, group, false);

        createListview(menu, resourceArray);

        Dialog menuDialog = new Dialog(context);
        menuDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuDialog.setContentView(menu);
        menuDialog.setCanceledOnTouchOutside(true);
        menuDialog.show();*/
    }

    private void createListview(View menu, int resourceArray) {
//        ListView listView = (ListView) menu.findViewById(R.id.menuListView);
//        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, resources
//                .getStringArray(resourceArray));
//        listView.setAdapter(adapter);

    }
}
