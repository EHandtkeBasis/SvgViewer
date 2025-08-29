package com.easirundemo.views;

import com.easirundemo.components.EasirunComponent;
import com.webforj.App;
import com.webforj.component.Composite;
import com.webforj.component.layout.flexlayout.FlexLayout;
import com.webforj.router.annotation.Route;
import com.webforj.utilities.Assets;

@Route("/")
public class DemoView extends Composite<FlexLayout> {

  private FlexLayout self = getBoundComponent();
  public DemoView() {

  self.setStyle("height", "100vh");
  self.setStyle("display", "flex");
  self.setStyle("align-items", "center");
  self.setStyle("justify-content", "center");
  self.setStyle("flex-direction", "column");

  EasirunComponent svgComponent = new EasirunComponent();
  svgComponent.setSvgContent(Assets.contentOf("/img/lifecycle-listeners.svg"));
  svgComponent.setSvgIdClickListener(id -> {
    App.console().log("SVG element clicked: " + id);
  });
  self.add(svgComponent);
  }
}
