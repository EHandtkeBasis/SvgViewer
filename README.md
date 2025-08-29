
# easirundemo


This project demonstrates a custom webforJ component: **SvgViewer**.

## SvgViewer

`SvgViewer` is a reusable UI component for displaying and interacting with SVG images. It provides:

- Zoom in/out controls
- Drag-to-pan functionality
- Clickable SVG elements (with callback for element id)
- Customizable styling via CSS

### Usage Example

````java
SvgViewer svgViewer = new SvgViewer();
svgViewer.setContent(Assets.contentOf("/img/lifecycle-listeners.svg"));
svgViewer.onClick(id -> {
	System.out.println("SVG element clicked: " + id);
});
parentLayout.add(svgViewer);
````

### Features

- **Zoom**: Use the + and - buttons to zoom the SVG in and out. Zoom step and limits are configurable.
- **Pan**: Click and drag the SVG area to pan.
- **SVG Element Click**: Listen for clicks on SVG elements by id using `onClick`.
- **Styling**: All styles are in `svgviewer.css` and can be customized.
- **JS Integration**: Drag-to-pan and SVG click detection are handled via `svgviewer.js`.

### Integration

1. Place your SVG file in the static resources folder (e.g., `src/main/resources/static/img/`).
2. Add the component to your view as shown above.
3. Optionally, register a click listener for SVG element ids.

---


## Prerequisites

- Java 21 or newer
- Maven 3.9+


## Getting Started

To run the application in development mode:

```bash
mvn jetty:run
```

Then open [http://localhost:8080](http://localhost:8080) in your browser.

This project is preconfigured to use the **Jetty Maven Plugin**, which makes development faster. It includes automatic scanning for class and resource changes.


### Jetty Auto-Reload (Hot Deployment)

By default, this project enables **Jetty's scan mode** using the following property:

```xml
<jetty.scan>1</jetty.scan>
```

This means Jetty will **poll for changes in compiled classes and resources every second**, allowing the app to **auto-reload** without restarting the server. This is great for quick feedback while developing UI or backend logic.

If you're using a live reload tool (like JRebel or similar), you may want to set this to `0` to disable it.

```xml
<jetty.scan>0</jetty.scan>
```


## Running Integration Tests

To run end-to-end and integration tests:

```bash
mvn verify
```

This command:
- Starts Jetty before tests using the `jetty:start` goal
- Runs integration tests using the **Failsafe Plugin** (tests ending with `*IT.java`)
- Shuts down Jetty after tests complete



## Building for Production

To create a WAR file for deployment:

```bash
mvn clean package -Pprod
```

The WAR file will be created in `target/easirundemo-1.0-SNAPSHOT.war`


## Learn More

Explore the webforJ ecosystem through our documentation and examples:

- [Full Documentation](https://docs.webforj.com)
- [Component Overview](https://docs.webforj.com/docs/components/overview)
- [Quick Tutorial](https://docs.webforj.com/docs/introduction/tutorial/overview)
- [Advanced Topics](https://docs.webforj.com/docs/advanced/overview)
