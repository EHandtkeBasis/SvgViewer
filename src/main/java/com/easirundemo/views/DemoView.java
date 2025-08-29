package com.easirundemo.views;

import com.easirundemo.components.SvgViewer;
import com.webforj.component.optiondialog.OptionDialog;
import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.router.annotation.Route;
import com.webforj.utilities.Assets;

@Route("/")
public class DemoView extends Composite<Div> {

  private Div self = getBoundComponent();

  public DemoView() {
    SvgViewer svgViewer = new SvgViewer();
    svgViewer.setSize("600px", "600px");
    svgViewer.setStyle("margin","var(--dwc-space)");
    svgViewer.setContent(Assets.contentOf("/img/lifecycle-listeners.svg"));

    svgViewer.onClick(id -> {
      OptionDialog.showMessageDialog("SVG element clicked: " + id, "SVG Click");
    });
    self.add(svgViewer);
  }
}
