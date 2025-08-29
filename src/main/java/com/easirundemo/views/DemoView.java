package com.easirundemo.views;

import com.easirundemo.components.SvgViewer;
import com.webforj.component.optiondialog.OptionDialog;
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

  SvgViewer svgViewer = new SvgViewer();
  svgViewer.setContent(Assets.contentOf("/img/lifecycle-listeners.svg"));
  svgViewer.onClick(id -> {
    OptionDialog.showMessageDialog("SVG element clicked: " + id, "SVG Click");
  });
  self.add(svgViewer);
  }
}
