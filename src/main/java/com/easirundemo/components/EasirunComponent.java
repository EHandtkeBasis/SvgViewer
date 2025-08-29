package com.easirundemo.components;


import com.webforj.component.element.event.ElementEventOptions;
import com.webforj.component.element.Element;
import com.webforj.component.element.event.ElementEvent;
import java.util.function.Consumer;

import com.webforj.annotation.StyleSheet;

import com.webforj.component.Composite;
import java.util.UUID;
import com.webforj.component.button.Button;
import com.webforj.component.button.ButtonTheme;
import com.webforj.component.html.elements.Div;
import com.webforj.component.layout.flexlayout.FlexLayout;

@StyleSheet("ws://easirun-component.css")
public class EasirunComponent extends Composite<Div> {

    // --- Fields ---
    private final Div header;
    private final Div content;
    private final Div imageDiv;
    private final Element svgContainer;
    private final String svgContainerId;
    private final Button plusButton;
    private final Button minusButton;
    private Consumer<String> svgIdClickListener;

    // Zoom configuration
    private double zoom = 1.0;
    private double zoomIncrement = 0.1;
    private double zoomMin = 0.2;
    private double zoomMax = 3.0;

    public EasirunComponent() {
        Div root = getBoundComponent();
        root.addClassName("standalone-header-content");

        header = new Div();
        header.addClassName("header");
        plusButton = new Button("+");
        plusButton.setTheme(ButtonTheme.PRIMARY);
        minusButton = new Button("-");
        FlexLayout buttonRow = FlexLayout.create(plusButton, minusButton)
            .horizontal()
            .align().center()
            .build();
        header.add(buttonRow);

        content = new Div();
        content.addClassName("content");
        imageDiv = new Div();
        imageDiv.addClassName("svg-image");
        svgContainerId = "svg-pan-" + UUID.randomUUID();
        svgContainer = new Element();
        svgContainer.addClassName("svg-inner");
        svgContainer.setAttribute("id", svgContainerId);
        svgContainer.setHtml("<!-- SVG content goes here -->");

        ElementEventOptions options = new ElementEventOptions();
        options.addData("elementId", "event.target.id");
        svgContainer.addEventListener("click", (ElementEvent event) -> {
            String clickedId = (String) event.getData().get("elementId");
            if (svgIdClickListener != null && clickedId != null && !clickedId.isEmpty()) {
                svgIdClickListener.accept(clickedId);
            }
        }, options);

        imageDiv.add(svgContainer);
        content.add(imageDiv);
        root.add(header, content);

        root.whenAttached().thenAccept(component -> {
            try {
                java.nio.file.Path jsPath = java.nio.file.Paths.get("src/main/resources/static/easirun-component.js");
                String jsCode = java.nio.file.Files.readString(jsPath);
                String callPan = "enableSvgPan('" + svgContainerId + "');";
                root.getElement().executeJs(jsCode + callPan);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        plusButton.addClickListener(e -> setZoom(zoom + zoomIncrement));
        minusButton.addClickListener(e -> setZoom(zoom - zoomIncrement));
        applyZoom();
    }

    /**
     * Allows a parent view or outside class to listen for SVG element id clicks.
     * @param listener Consumer that receives the clicked element id
     */
    public void setSvgIdClickListener(Consumer<String> listener) {
        this.svgIdClickListener = listener;
    }

    /**
     * Sets the SVG as the content of the SVG container.
     * @param svgContent SVG as a string
     */
    public void setSvgContent(String svgContent) {
        svgContainer.setHtml(svgContent);
    }

    private void applyZoom() {
        svgContainer.setStyle("transform", "scale(" + zoom + ")");
        svgContainer.setStyle("transform-origin", "top center");
    }

    public void setZoom(double value) {
        if (value < zoomMin) value = zoomMin;
        if (value > zoomMax) value = zoomMax;
        this.zoom = value;
        applyZoom();
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoomIncrement(double increment) {
        this.zoomIncrement = increment;
    }

    public void setZoomMin(double min) {
        this.zoomMin = min;
    }

    public void setZoomMax(double max) {
        this.zoomMax = max;
    }
}
