// Generated code from Butter Knife. Do not modify!
package us.zoom.sdkexample;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Home$$ViewInjector {
  public static void inject(Finder finder, final us.zoom.sdkexample.Home target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624067, "field 'appbar'");
    target.appbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131624066, "field 'navigation_drawer'");
    target.navigation_drawer = (android.support.v4.widget.DrawerLayout) view;
  }

  public static void reset(us.zoom.sdkexample.Home target) {
    target.appbar = null;
    target.navigation_drawer = null;
  }
}
