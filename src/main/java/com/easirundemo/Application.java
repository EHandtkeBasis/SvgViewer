package com.easirundemo;

import com.webforj.App;
import com.webforj.annotation.AppProfile;
import com.webforj.annotation.Routify;
import com.webforj.annotation.StyleSheet;

@Routify(packages = "com.easirundemo.views")
@StyleSheet("ws://app.css")
@AppProfile(name = "easirundemo", shortName = "easirundemo")
public class Application extends App {
}
