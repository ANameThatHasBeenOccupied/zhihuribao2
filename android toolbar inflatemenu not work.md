##ToolBar inflateMenu 不生效
在使用ToolBar的时候，发现调用

	Toolbar.inflateMenu(R.menu.toolbar_menu);

一直显示不出ToolBar右上角的图标。其中menu文件如下：
	
	<?xml version="1.0" encoding="utf-8"?>
	<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".ContentActivity">
    <item
        android:id="@+id/action_login"
        android:icon="@mipmap/ic_drawer_am"
        android:title="Login"
        app:showAsAction="always" />
    <item
        android:id="@+id/action_settiong"
        android:orderInCategory="100"
        android:title="action_settings"
        app:showAsAction="never" />
    <item
        android:id="@+id/action_night"
        android:orderInCategory="100"
        android:title="Night"
        app:showAsAction="never"></item>
	</menu>

然而，发现不调用ToolBar本身的接口，调用Activity本身创建menu的接口却可以显示!!

	//设置ToolBar的选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

这个问题纠结，查了很久！总算找到答案了：

[http://stackoverflow.com/questions/26511981/toolbar-inflatemenu-seems-to-do-nothing](http://stackoverflow.com/questions/26511981/toolbar-inflatemenu-seems-to-do-nothing "android ToolBar inflatemenu not work")

> If you are calling setSupportActionBar() you don't need to use toolbar.inflateMenu() because the Toolbar is acting as your ActionBar. All menu related callbacks are via the default ones. The only time you need to call toolbar.inflateMenu() is when you are using the Toolbar as a standalone widget. 

意思就是说，要想让Toolbar本身的inflateMenu生效，则必须删去这两句代码！！！

	setSupportActionBar(toolbar);
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);

This is the answer!!